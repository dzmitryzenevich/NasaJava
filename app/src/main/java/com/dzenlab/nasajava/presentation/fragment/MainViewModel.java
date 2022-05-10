package com.dzenlab.nasajava.presentation.fragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava2.PagingRx;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;
import com.dzenlab.nasajava.usecase.StateUrlPictureUseCase;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends ViewModel {

    private final SavePositionAndIdUseCase savePositionAndIdUseCase;

    private final GetItemIdUseCase getItemIdUseCase;

    private final StateUrlPictureUseCase stateUrlPictureUseCase;

    private final LoadItemsUseCase loadItemsUseCase;

    private final DeleteItemUseCase deleteItemUseCase;

    private MutableLiveData<Integer> itemId;

    private LiveData<PagingData<ItemNet>> itemList;

    private MutableLiveData<StateUrlPictureSP> stateAndUrl;

    private MutableLiveData<Boolean> refreshList;

    private Disposable disposable;


    public MainViewModel(SavePositionAndIdUseCase savePositionAndIdUseCase,
                         GetItemIdUseCase getItemIdUseCase,
                         StateUrlPictureUseCase stateUrlPictureUseCase,
                         LoadItemsUseCase loadItemsUseCase,
                         DeleteItemUseCase deleteItemUseCase) {

        this.savePositionAndIdUseCase = savePositionAndIdUseCase;

        this.getItemIdUseCase = getItemIdUseCase;

        this.stateUrlPictureUseCase = stateUrlPictureUseCase;

        this.loadItemsUseCase = loadItemsUseCase;

        this.deleteItemUseCase = deleteItemUseCase;

        disposable = null;

        initId();

        initList();

        initPicture();

        initialRefreshList();
    }

    @Override
    protected void onCleared() {

        super.onCleared();

        if(disposable != null && !disposable.isDisposed()) {

            disposable.dispose();

            disposable = null;
        }
    }

    public LiveData<Integer> getItemId() {

        return itemId;
    }

    public LiveData<PagingData<ItemNet>> getItemList() {

        return itemList;
    }

    public LiveData<StateUrlPictureSP> getStateAndUrl() {

        return stateAndUrl;
    }

    public LiveData<Boolean> getRefreshList() {

        return refreshList;
    }

    public void getPositionOK() {

        itemId.setValue(null);
    }

    public void setPositionAndId(int position, int id) {

        savePositionAndIdUseCase.execute(position, id).subscribe();
    }

    public void deleteItem(ItemNet itemNet) {

        disposable = deleteItemUseCase.execute(itemNet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshList);
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

    private void initId() {

        int id = getItemIdUseCase.execute().blockingGet().getData();

        itemId = new MutableLiveData<>(id);
    }

    private void initList() {

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

        Flowable<PagingData<ItemNet>> flowableItemNet =
                PagingRx.getFlowable(loadItemsUseCase.execute(10));

        Flowable<PagingData<ItemNet>> flowable = PagingRx.cachedIn(flowableItemNet, viewModelScope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .publish()
                .autoConnect();

        itemList = LiveDataReactiveStreams.fromPublisher(flowable);
    }

    private void initPicture() {

        StateUrlPictureSP stateUrlPictureSP = stateUrlPictureUseCase.execute(null, null);

        stateAndUrl = new MutableLiveData<>(stateUrlPictureSP);
    }

    private void initialRefreshList() {

        refreshList = new MutableLiveData<>();
    }

    private void refreshList() {

        refreshList.setValue(true);

        refreshList.setValue(false);
    }
}