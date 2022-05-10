package com.dzenlab.nasajava.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava2.RxPagingSource;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.PagingDataFull;
import com.dzenlab.nasajava.models.PagingDataSP;
import com.dzenlab.nasajava.models.QueryDB;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class LoadItemsUseCase {

    private final ItemRepository itemRepository;


    public LoadItemsUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Pager<Integer, ItemNet> execute(int pageSize) {

        PagingConfig pagingConfig = new PagingConfig(pageSize);

        return new Pager<>(pagingConfig, null, () -> new RxPagingSource<Integer, ItemNet>() {

            @NonNull
            @Override
            public Single<LoadResult<Integer, ItemNet>> loadSingle(
                    @NonNull LoadParams<Integer> loadParams) {

                return itemRepository.getCount()
                        .flatMapCompletable(count -> saveItemList(count))
                        .andThen(itemRepository.getPosition())
                        .zipWith(itemRepository.getMinPage(), (position, page) ->
                                getPagingData(position, page, loadParams.getKey(), pageSize))
                        .map(pagingDataFull -> setItemList(pagingDataFull, pageSize,
                                loadParams.getLoadSize(),
                                        itemRepository.getAll(new QueryDB(loadParams.getLoadSize(),
                                                pagingDataFull.getPage() * pageSize))))
                        .subscribeOn(Schedulers.io())
                        .map(pagingData -> toLoadResult(pagingData))
                        .onErrorReturn(LoadResult.Error::new);
            }

            @Nullable
            @Override
            public Integer getRefreshKey(
                    @NonNull PagingState<Integer, ItemNet> pagingState) {

                Integer anchorPosition = pagingState.getAnchorPosition();

                if (anchorPosition == null) {

                    return null;
                }

                LoadResult.Page<Integer, ItemNet> anchorPage =
                        pagingState.closestPageToPosition(anchorPosition);

                if (anchorPage == null) {

                    return null;
                }

                Integer minPage = null;

                Integer nextKey = anchorPage.getNextKey();

                if (nextKey != null) {

                    int numberPage = anchorPage.getData().size() / pageSize;

                    if(anchorPage.getData().size() % pageSize != 0) {

                        numberPage += 1;
                    }

                    minPage = nextKey - numberPage;
                }

                Integer prevKey = anchorPage.getPrevKey();

                if (prevKey != null) {

                    minPage = prevKey + 1;
                }

                if(minPage != null) {

                    itemRepository.setMinPage(new PagingDataSP(minPage)).subscribe();

                    return minPage;
                }

                return null;
            }
        });
    }

    private Completable saveItemList(int count) {

        if(count == 0) {

            return itemRepository.getItemList().flatMapCompletable(itemRepository::insertAll);

        } else {

            return Completable.complete();
        }
    }

    private PagingDataFull getPagingData(PagingDataSP position,
                                         PagingDataSP minPage,
                                         @Nullable Integer systemPage,
                                         int pageSize) {

        int page;

        if(systemPage == null) {

            page = minPage.getData() + (position.getData() / pageSize);

            itemRepository.setMinPage(new PagingDataSP(page)).subscribe();

        } else {

            page = systemPage;

            if(page < minPage.getData()) {

                itemRepository.setMinPage(new PagingDataSP(page)).subscribe();
            }
        }

        PagingDataFull pagingDataFull = new PagingDataFull();

        pagingDataFull.setPage(page);

        return pagingDataFull;
    }

    private PagingDataFull setItemList(PagingDataFull pagingDataFull,
                                       int pageSize, int loadSize,
                                       List<ItemNet> list) {

        pagingDataFull.setList(list);

        if(loadSize == list.size()) {

            pagingDataFull.setNextPage(pagingDataFull.getPage() + loadSize / pageSize);

        } else {

            pagingDataFull.setNextPage(null);
        }

        if(pagingDataFull.getPage() == 0) {

            pagingDataFull.setPrevPage(null);

        } else {

            pagingDataFull.setPrevPage(pagingDataFull.getPage() - 1);
        }

        return pagingDataFull;
    }

    private PagingSource.LoadResult<Integer, ItemNet> toLoadResult(
            PagingDataFull pagingDataFull) {

        return new PagingSource.LoadResult.Page<>(
                pagingDataFull.getList(),
                pagingDataFull.getPrevPage(),
                pagingDataFull.getNextPage(),
                PagingSource.LoadResult.Page.COUNT_UNDEFINED,
                PagingSource.LoadResult.Page.COUNT_UNDEFINED);
    }
}
