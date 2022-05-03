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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dzenlab.nasajava.R;
import com.dzenlab.nasajava.app.App;
import com.dzenlab.nasajava.databinding.MainFragmentBinding;
import com.dzenlab.nasajava.presentation.adapter.ItemAdapter;
import com.dzenlab.nasajava.presentation.utils.PicassoHelper;
import com.dzenlab.nasajava.presentation.utils.SwipeTouchListener;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    public MainViewModelFactory mainViewModelFactory;

    private MainFragmentBinding binding;

    private MainViewModel viewModel;

    private ConstraintLayout constraintLayout;

    private RecyclerView recyclerView;

    private CircularProgressIndicator progressBar;

    private String url;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, mainViewModelFactory)
                .get(MainViewModel.class);

        binding = MainFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        progressBar = binding.cardViewPicture.progressIndicator;

        constraintLayout = binding.main;

        TextView message = binding.messageTextView;

        recyclerView = binding.recyclerView;

        ItemAdapter itemAdapter = new ItemAdapter(url ->
                viewModel.stateUrlPicture(true, url), new ItemAdapter.ItemDiff());

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

                long id = itemAdapter.getItemId(viewHolder.getAdapterPosition());

                viewModel.deleteItemFromDatabase(id);
            }

        }).attachToRecyclerView(recyclerView);

        recyclerView.setOnTouchListener((view, motionEvent) ->
                viewModel.stateUrlPicture(false, null));

        ImageView imageView = binding.cardViewPicture.imageView;

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

        viewModel.getItemList().observe(getViewLifecycleOwner(), response -> {

            itemAdapter.submitList(response.getList());

            if(response.getError() == null) {

                if(response.getList().size() == 0) {

                    viewModel.getItemListFromNetwork();

                } else {

                    viewModel.installPosition();
                }

            } else {

                if(!response.getError().equals("")) {

                    String text = getString(R.string.load_data_from_db_error);

                    text += "\n";

                    text += response.getError();

                    message.setText(text);

                } else {

                    message.setText(R.string.load_data_from_db_error);
                }

                message.setVisibility(View.VISIBLE);
            }
        });

        viewModel.isLoadData().observe(getViewLifecycleOwner(), response -> {

            if(response) {

                viewModel.startInterval();

            } else {

                viewModel.stopInterval();
            }
        });

        viewModel.getMessage().observe(getViewLifecycleOwner(), response -> {

            switch (response.getCode()) {

                case CODE_START_LOAD_DATA:

                    message.setText(R.string.load_data_start);

                    message.setVisibility(View.VISIBLE);

                    break;

                case CODE_STOP_LOAD_DATA:

                    message.setVisibility(View.INVISIBLE);

                    message.setText("");

                    break;

                case CODE_NONE:

                    break;

                case CODE_LOAD_DATA_0:

                    message.setText(R.string.load_data_0);

                    message.setVisibility(View.VISIBLE);

                    break;

                case CODE_LOAD_DATA_1:

                    message.setText(R.string.load_data_1);

                    message.setVisibility(View.VISIBLE);

                    break;

                case CODE_LOAD_DATA_2:

                    message.setText(R.string.load_data_2);

                    message.setVisibility(View.VISIBLE);

                    break;

                case CODE_LOAD_DATA_3:

                    message.setText(R.string.load_data_3);

                    message.setVisibility(View.VISIBLE);

                    break;

                case CODE_EXCEPTION:

                    itemAdapter.submitList(new ArrayList<>());

                    String text = getString(R.string.message_exception);

                    if(response.getMessage() != null) {

                        text += ": ";

                        text += response.getMessage();
                    }

                    message.setText(text);

                    message.setVisibility(View.VISIBLE);

                    break;
            }
        });

        viewModel.getPosition().observe(getViewLifecycleOwner(), response ->
                recyclerView.scrollToPosition(response));

        Picasso picasso = PicassoHelper.getPicasso(requireContext());

        url = "";

        viewModel.getStateAndUrl().observe(getViewLifecycleOwner(), response -> {

            boolean newIsOpen = response.isOpen();

            String newUrl = response.getUrl();

            showOrHidePicture(newIsOpen);

            if(!url.equals(newUrl) && !newUrl.equals("") && newIsOpen) {

                url = newUrl;

                progressBar.show();

                picasso.load(url)
                        .error(R.drawable.ic_load_error)
                        .into(imageView, new Callback() {

                            @Override
                            public void onSuccess() {

                                progressBar.hide();
                            }

                            @Override
                            public void onError(Exception e) {

                                progressBar.hide();

                                url = "";
                            }
                        });

            } else {

                progressBar.hide();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        LinearLayoutManager linearLayoutManager =
                ((LinearLayoutManager)recyclerView.getLayoutManager());

        if(linearLayoutManager != null) {

            int position = linearLayoutManager.findFirstVisibleItemPosition();

            viewModel.setPosition(position);
        }

        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        App app = (App) requireActivity().getApplicationContext();

        app.appComponent.fragmentComponent().create().inject(this);
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
}