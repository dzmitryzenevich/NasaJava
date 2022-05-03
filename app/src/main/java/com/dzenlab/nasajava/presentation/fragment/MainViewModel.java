package com.dzenlab.nasajava.presentation.fragment;

import static com.dzenlab.nasajava.presentation.constants.Code.*;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.dzenlab.nasajava.models.ItemDeleteDB;
import com.dzenlab.nasajava.models.NumberSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.presentation.constants.Code;
import com.dzenlab.nasajava.presentation.models.Message;
import com.dzenlab.nasajava.models.ResponseDB;
import com.dzenlab.nasajava.usecase.database.DeleteItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.database.GetItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.network.GetItemListFromNetworkUseCase;
import com.dzenlab.nasajava.usecase.sharepref.GetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.database.SaveItemInDatabaseUseCase;
import com.dzenlab.nasajava.usecase.sharepref.SetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.sharepref.StateUrlPictureUseCase;
import java.util.concurrent.TimeUnit;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final GetLastNumberUseCase getLastNumberUseCase;

    private final SetLastNumberUseCase setLastNumberUseCase;

    private final StateUrlPictureUseCase stateUrlPictureUseCase;

    private final GetItemListFromNetworkUseCase getItemListFromNetworkUseCase;

    private final SaveItemInDatabaseUseCase saveItemInDatabaseUseCase;

    private final DeleteItemFromDatabaseUseCase deleteItemFromDatabaseUseCase;

    private final LiveData<ResponseDB> itemList;

    private final MutableLiveData<Boolean> isLoadData = new MutableLiveData<>(false);

    private final MutableLiveData<Integer> position = new MutableLiveData<>();

    private final MutableLiveData<Message> message =
            new MutableLiveData<>(new Message(null, CODE_NONE));

    private final MutableLiveData<StateUrlPictureSP> stateAndUrl = new MutableLiveData<>();

    private Disposable loadDataDisposable;

    private Disposable deleteDisposable;

    private Disposable intervalDisposable;


    public MainViewModel(GetLastNumberUseCase getLastNumberUseCase,
                         SetLastNumberUseCase setLastNumberUseCase,
                         StateUrlPictureUseCase stateUrlPictureUseCase,
                         GetItemListFromNetworkUseCase getItemListFromNetworkUseCase,
                         GetItemFromDatabaseUseCase getItemFromDatabaseUseCase,
                         SaveItemInDatabaseUseCase saveItemInDatabaseUseCase,
                         DeleteItemFromDatabaseUseCase deleteItemFromDatabaseUseCase) {

        this.getLastNumberUseCase = getLastNumberUseCase;

        this.setLastNumberUseCase = setLastNumberUseCase;

        this.stateUrlPictureUseCase = stateUrlPictureUseCase;

        this.getItemListFromNetworkUseCase = getItemListFromNetworkUseCase;

        this.saveItemInDatabaseUseCase = saveItemInDatabaseUseCase;

        this.deleteItemFromDatabaseUseCase = deleteItemFromDatabaseUseCase;

        Flowable<ResponseDB> flowable = getItemFromDatabaseUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .publish()
                .autoConnect();

        this.itemList = LiveDataReactiveStreams.fromPublisher(flowable);

        StateUrlPictureSP stateUrlPictureSP = stateUrlPictureUseCase.execute(null, null);

        this.stateAndUrl.setValue(stateUrlPictureSP);
    }

    @Override
    protected void onCleared() {

        super.onCleared();

        if(loadDataDisposable != null) loadDataDisposable.dispose();

        if(deleteDisposable != null) deleteDisposable.dispose();

        if(intervalDisposable != null) intervalDisposable.dispose();
    }

    public LiveData<ResponseDB> getItemList() {

        return itemList;
    }

    public MutableLiveData<Boolean> isLoadData() {

        return isLoadData;
    }

    public LiveData<Message> getMessage() {

        return message;
    }

    public MutableLiveData<Integer> getPosition() {

        return position;
    }

    public MutableLiveData<StateUrlPictureSP> getStateAndUrl() {

        return stateAndUrl;
    }

    public void getItemListFromNetwork() {

        if(isLoadData.getValue() != null && !isLoadData.getValue()) {

            isLoadData.setValue(true);

            loadDataDisposable = getItemListFromNetworkUseCase.execute()
                    .flatMapCompletable(saveItemInDatabaseUseCase::execute)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> isLoadData.setValue(false), this::loadDataError);
        }
    }

    public void startInterval() {

        if(intervalDisposable == null || intervalDisposable.isDisposed()) {

            message.setValue(new Message(null, CODE_START_LOAD_DATA));

            intervalDisposable = Observable.interval(200, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(time -> message.setValue(timer(time)), this::intervalError);
        }
    }

    public void stopInterval() {

        if(intervalDisposable != null && !intervalDisposable.isDisposed()) {

            message.setValue(new Message(null, CODE_STOP_LOAD_DATA));

            message.setValue(new Message(null, CODE_NONE));

            intervalDisposable.dispose();
        }
    }

    public void deleteItemFromDatabase(long id) {

        deleteDisposable = deleteItemFromDatabaseUseCase.execute(new ItemDeleteDB(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> this.setPosition(0));
    }

    public void setPosition(int position) {

        setLastNumberUseCase.execute(new NumberSP(position)).subscribe();
    }

    public void installPosition() {

        position.setValue(getLastNumberUseCase.execute().getNumber());
    }

    public boolean stateUrlPicture(boolean isOpen, @Nullable String url) {

        boolean flag = false;

        StateUrlPictureSP data = stateAndUrl.getValue();

        if(data != null) {

            if(url == null) {

                if(data.isOpen()) {

                    flag =  stateUrlPictureUseCase.execute(false, null).isOpen();

                    stateAndUrl.setValue(new StateUrlPictureSP(false, data.getUrl()));
                }

            } else {

                if(isOpen != data.isOpen() || !url.equals(data.getUrl())) {

                    boolean newIsOpen;

                    if(isOpen != data.isOpen()) {

                        newIsOpen = isOpen;

                    } else {

                        newIsOpen = data.isOpen();
                    }

                    String newURL;

                    if(!url.equals(data.getUrl())) {

                        newURL = url;

                    } else {

                        newURL = data.getUrl();
                    }

                    flag = stateUrlPictureUseCase.execute(newIsOpen, newURL).isOpen();

                    stateAndUrl.setValue(new StateUrlPictureSP(newIsOpen, newURL));

                } else {

                    if(isOpen) {

                        flag = stateUrlPictureUseCase.execute(false, null).isOpen();

                        stateAndUrl.setValue(new StateUrlPictureSP(false, url));
                    }
                }
            }
        }

        return flag;
    }

    private Message timer(long time) {

        Code code;

        if(time % 4 == 1) {

            code = CODE_LOAD_DATA_1;

        } else if(time % 4 == 2) {

            code = CODE_LOAD_DATA_2;

        } else if(time % 4 == 3) {

            code = CODE_LOAD_DATA_3;

        } else {

            code = CODE_LOAD_DATA_0;
        }

        return new Message(null, code);
    }

    private void loadDataError(Throwable throwable) {

        isLoadData.setValue(false);

        message.setValue(new Message(throwable.getMessage(), CODE_EXCEPTION));
    }

    private void intervalError(Throwable throwable) {

        message.setValue(new Message(throwable.getMessage(), CODE_EXCEPTION));
    }
}