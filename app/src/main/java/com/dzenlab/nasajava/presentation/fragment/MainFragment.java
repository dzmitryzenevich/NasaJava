package com.dzenlab.nasajava.presentation.fragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.os.Looper;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dzenlab.nasajava.R;
import com.dzenlab.nasajava.app.App;
import com.dzenlab.nasajava.databinding.MainFragmentBinding;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.presentation.adapter.ItemAdapter;
import com.dzenlab.nasajava.presentation.utils.BackPressedListener;
import com.dzenlab.nasajava.presentation.utils.PicassoHelper;
import com.dzenlab.nasajava.presentation.utils.SwipeTouchListener;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class MainFragment extends Fragment implements BackPressedListener {

    @Inject
    public MainViewModelFactory mainViewModelFactory;

    private MainFragmentBinding binding;

    private MainViewModel viewModel;

    private ConstraintLayout constraintLayout;

    private RecyclerView recyclerView;

    private CircularProgressIndicator pictureProgressBar;

    private CircularProgressIndicator progressBar;

    private ImageView imageView;

    private TextView message;

    private Handler handler;

    private Integer itemId;

    private boolean isOpen;

    private String url;

    private ItemAdapter itemAdapter;

    private boolean firstStart;


    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, mainViewModelFactory)
                .get(MainViewModel.class);

        binding = MainFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        init();

        position();

        recyclerView();

        image();

        return root;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        handler.removeCallbacksAndMessages(null);

        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        App app = (App) requireActivity().getApplicationContext();

        app.appComponent.fragmentComponent().create().inject(this);
    }

    @Override
    public void onStart() {

        super.onStart();

        if(recyclerView != null) {

            recyclerView.addOnScrollListener(scrollListener);
        }
    }

    @Override
    public void onStop() {

        super.onStop();

        if(recyclerView != null) {

            recyclerView.removeOnScrollListener(scrollListener);
        }
    }

    @Override
    public void onPause() {

        super.onPause();

        LinearLayoutManager linearLayoutManager =
                ((LinearLayoutManager)recyclerView.getLayoutManager());

        if(linearLayoutManager != null) {

            int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

            if(position >= 0) {

                ItemNet item = itemAdapter.getItemNet(position);

                if(item != null) {

                    viewModel.setPositionAndId(position, item.getId());
                }
            }
        }
    }

    @Override
    public boolean isCloseApp() {

        boolean isClose = !isOpen;

        if(isOpen) viewModel.stateUrlPicture(false, null);

        return isClose;
    }

    private void init() {

        constraintLayout = binding.main;

        itemAdapter = new ItemAdapter(callback, new ItemAdapter.ItemDiff());

        recyclerView = binding.recyclerView;

        message = binding.messageTextView;

        pictureProgressBar = binding.cardViewPicture.progressIndicator;

        progressBar = binding.progressIndicator;

        handler = new Handler(Looper.getMainLooper());

        itemId = null;

        isOpen = false;

        url = "";

        firstStart = true;
    }

    private void position() {

        viewModel.getItemId().observe(getViewLifecycleOwner(), response -> {

            if(response != null) {

                itemId = response;

                viewModel.getPositionOK();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void recyclerView() {

        recyclerView.setItemAnimator(null);

        recyclerView.setAdapter(itemAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) { return false; }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {

                ItemNet item = itemAdapter.getItemNet(viewHolder.getAbsoluteAdapterPosition());

                if(item != null) {

                    viewModel.deleteItem(item);
                }
            }

        }).attachToRecyclerView(recyclerView);

        itemAdapter.addLoadStateListener(state -> {

            LoadState.Error stateError = null;

            if(state.getSource().getRefresh() instanceof LoadState.Loading) {

                handler.removeCallbacksAndMessages(null);

                if(itemId != null) {

                    recyclerView.setVisibility(View.GONE);

                    message.setText(R.string.load_data);

                    message.setVisibility(View.VISIBLE);

                    progressBar.show();

                } else {

                    handler.postDelayed(() -> {

                        message.setText(R.string.load_data);

                        message.setVisibility(View.VISIBLE);

                        progressBar.show();

                    }, 100);
                }

            } else if(state.getSource().getRefresh() instanceof LoadState.NotLoading) {

                handler.removeCallbacksAndMessages(null);

                if(itemId != null) {

                    handler.postDelayed(() -> {

                        LinearLayoutManager linearLayoutManager =
                                ((LinearLayoutManager)recyclerView.getLayoutManager());

                        if(linearLayoutManager != null) {

                            for(ItemNet itemNet : itemAdapter.snapshot().getItems()) {

                                if(itemNet.getId() == itemId) {

                                    int id = itemAdapter.snapshot().indexOf(itemNet);

                                    linearLayoutManager.scrollToPositionWithOffset(id, 0);
                                }
                            }
                        }

                        itemId = null;

                        new Handler(Looper.getMainLooper()).postDelayed(() -> {

                            message.setText("");

                            message.setVisibility(View.GONE);

                            progressBar.hide();

                            recyclerView.setVisibility(View.VISIBLE);

                        },50);

                    }, 500);

                } else {

                    message.setText("");

                    message.setVisibility(View.GONE);

                    progressBar.hide();
                }

            } else if(state.getSource().getAppend() instanceof LoadState.Error) {

                stateError = (LoadState.Error) state.getSource().getAppend();

            } else if(state.getSource().getRefresh() instanceof LoadState.Error) {

                stateError = (LoadState.Error) state.getSource().getRefresh();

            } else if(state.getSource().getPrepend() instanceof LoadState.Error) {

                stateError = (LoadState.Error) state.getSource().getPrepend();

            } else {

                handler.removeCallbacksAndMessages(null);

                message.setText("");

                message.setVisibility(View.GONE);

                recyclerView.setVisibility(View.GONE);

                new Handler(Looper.getMainLooper()).postDelayed(progressBar::hide, 50);
            }

            if(stateError != null) {

                handler.removeCallbacksAndMessages(null);

                recyclerView.setVisibility(View.GONE);

                new Handler(Looper.getMainLooper()).postDelayed(progressBar::hide, 50);

                String error = stateError.getError().getMessage();

                if(error != null) {

                    String errorMessage = getString(R.string.load_data_error) + "\n" + error;

                    message.setText(errorMessage);

                } else {

                    message.setText(R.string.load_data_error);
                }

                message.setVisibility(View.VISIBLE);
            }

            return null;
        });

        viewModel.getItemList().observe(getViewLifecycleOwner(), response ->
                itemAdapter.submitData(getLifecycle(), response));

        viewModel.getRefreshList().observe(getViewLifecycleOwner(), response -> {

            if(response) {

                itemAdapter.refresh();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void image() {

        imageView = binding.cardViewPicture.imageView;

        imageView.setOnTouchListener(new SwipeTouchListener(requireContext()) {

            @Override
            public void onSwipeRight() {

                super.onSwipeRight();

                if (getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_LANDSCAPE) {

                    viewModel.stateUrlPicture(false, null);
                }
            }

            @Override
            public void onSwipeUp() {

                super.onSwipeUp();

                if (getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT) {

                    viewModel.stateUrlPicture(false, null);
                }
            }
        });

        Picasso picasso = PicassoHelper.getPicasso(requireContext());

        viewModel.getStateAndUrl().observe(getViewLifecycleOwner(), response -> {

            isOpen = response.isOpen();

            String newUrl = response.getUrl();

            showOrHidePicture(isOpen);

            if(!url.equals(newUrl) && !newUrl.equals("") && isOpen) {

                url = newUrl;

                pictureProgressBar.show();

                try {

                    picasso.load(url)
                            .error(R.drawable.ic_load_error)
                            .into(imageView, new Callback() {

                                @Override
                                public void onSuccess() {

                                    new Handler(Looper.myLooper())
                                            .postDelayed(pictureProgressBar::hide, 100);
                                }

                                @Override
                                public void onError(Exception e) {

                                    new Handler(Looper.myLooper())
                                            .postDelayed(pictureProgressBar::hide, 100);

                                    url = "";
                                }
                            });

                } catch (Exception e) {

                    viewModel.stateUrlPicture(false, null);

                    new Handler(Looper.myLooper()).postDelayed(pictureProgressBar::hide, 100);

                    url = "";
                }
            }
        });
    }

    private void showOrHidePicture(boolean isShow) {

        ConstraintSet set = new ConstraintSet();

        set.clone(constraintLayout);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {

            set.clear(R.id.card_view_picture, ConstraintSet.START);

            if(isShow) {

                set.connect(R.id.card_view_picture, ConstraintSet.START,
                        R.id.guide_line, ConstraintSet.END);

            } else {

                set.connect(R.id.card_view_picture, ConstraintSet.START,
                        ConstraintSet.PARENT_ID, ConstraintSet.END);
            }

        } else {

            set.clear(R.id.card_view_picture, ConstraintSet.BOTTOM);

            if(isShow) {

                set.connect(R.id.card_view_picture, ConstraintSet.BOTTOM,
                        R.id.guide_line, ConstraintSet.TOP);

            } else {

                set.connect(R.id.card_view_picture, ConstraintSet.BOTTOM,
                        ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            }
        }

        TransitionManager.beginDelayedTransition(constraintLayout);

        set.applyTo(constraintLayout);
    }

    private final ItemAdapter.ClickCallback callback = url ->
            viewModel.stateUrlPicture(true, url);

    private final RecyclerView.OnScrollListener scrollListener =  new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);

            if(firstStart) {

                firstStart = false;

                return;
            }

            if(isOpen) viewModel.stateUrlPicture(false, null);
        }
    };
}