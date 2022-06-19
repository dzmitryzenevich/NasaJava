package com.dzenlab.nasajava.presentation.fragment;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava2.PagingRx;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.presentation.model.StateUrlPicture;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends ViewModel {

    private final SavePositionAndIdUseCase savePositionAndIdUseCase;

    private final GetItemIdUseCase getItemIdUseCase;

    private final LoadItemsUseCase loadItemsUseCase;

    private final DeleteItemUseCase deleteItemUseCase;

    private MutableLiveData<Integer> itemId;

    private LiveData<PagingData<ItemNet>> itemList;

    private MutableLiveData<StateUrlPicture> stateAndUrl;

    private MutableLiveData<Boolean> refreshList;

    private Disposable disposable;


    public MainViewModel(SavePositionAndIdUseCase savePositionAndIdUseCase,
                         GetItemIdUseCase getItemIdUseCase,
                         LoadItemsUseCase loadItemsUseCase,
                         DeleteItemUseCase deleteItemUseCase) {

        this.savePositionAndIdUseCase = savePositionAndIdUseCase;

        this.getItemIdUseCase = getItemIdUseCase;

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

    public LiveData<StateUrlPicture> getStateAndUrl() {

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

    public void stateUrlPicture(boolean isOpen, @Nullable String url) {

        StateUrlPicture data = stateAndUrl.getValue();

        if(data != null) {

            if(url == null) {

                if(data.isOpen()) {

                    stateAndUrl.setValue(new StateUrlPicture(false, data.getUrl()));
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

                    stateAndUrl.setValue(new StateUrlPicture(newIsOpen, newURL));

                } else {

                    if(isOpen) {

                        stateAndUrl.setValue(new StateUrlPicture(false, url));
                    }
                }
            }
        }
    }

    private void initId() {

        int id = getItemIdUseCase.execute().blockingGet().getData();

        itemId = new MutableLiveData<>(id);
    }

    private void initList() {

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

        Flowable<PagingData<ItemNet>> flowableItemNet =
                PagingRx.getFlowable(loadItemsUseCase.execute(10));

        @SuppressLint("UnsafeOptInUsageWarning")
        Flowable<PagingData<ItemNet>> flowable = PagingRx.cachedIn(flowableItemNet, viewModelScope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .publish()
                .autoConnect();

        itemList = LiveDataReactiveStreams.fromPublisher(flowable);
    }

    private void initPicture() {

        stateAndUrl = new MutableLiveData<>(new StateUrlPicture(false, ""));
    }

    private void initialRefreshList() {

        refreshList = new MutableLiveData<>();
    }

    private void refreshList() {

        refreshList.setValue(true);

        refreshList.setValue(false);
    }
}