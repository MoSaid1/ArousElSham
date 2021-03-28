package com.example.arouselsham.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.ui.add.AddDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private List<String> meals, images;

    @BindView(R.id.txt_good_evening)
    TextView goodEveningTxt;

    @BindView(R.id.recycler_categories)
    RecyclerView categoryRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                goodEveningTxt.setText(s);
            }
        });

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document("MenuMainTags")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        meals = (List<String>) task.getResult().get("enTags");
                        images = (List<String>) task.getResult().get("images");
                        CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), meals, images);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        categoryRecycler.setLayoutManager(layoutManager);
                        categoryRecycler.setAdapter(adapter);
                    }
                });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_add:
                AddDialog addDialog = new AddDialog();
                addDialog.show(getActivity().getSupportFragmentManager(), "Add Dialog");
                break;
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}