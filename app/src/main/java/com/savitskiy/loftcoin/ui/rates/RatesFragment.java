package com.savitskiy.loftcoin.ui.rates;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.savitskiy.loftcoin.BaseComponent;
import com.savitskiy.loftcoin.R;
import com.savitskiy.loftcoin.databinding.FragmentRatesBinding;
import static com.savitskiy.loftcoin.R.array.currencies_array;
import javax.inject.Inject;

import timber.log.Timber;

public class RatesFragment extends Fragment {
    @Inject RatesAdapter adapter;

    private NavController navController;

    private FragmentRatesBinding binding;

    private RatesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RatesComponent component = DaggerRatesComponent.builder()
                .baseComponent(BaseComponent.get(requireContext()))
                .fragment(this)
                .build();
        component.inject(this); //example of injection
        navController = Navigation.findNavController(requireActivity(), R.id.main_host);
        viewModel = new ViewModelProvider(this)
                .get(RatesViewModel.class);
        if (viewModel.isInitialized().compareAndSet(false, true)) {
            component.inject(viewModel);
        }
        viewModel.getCoins().observe(this, adapter::submitList);
        viewModel.observeCurrencyChange();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRatesBinding.inflate(inflater, container, false);
        Timber.d("%s", this);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.converter, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.currency_selector:
                navController.navigate(R.id.action_currency_dialog);
                return true;
            case R.id.menu_sorting:
                //to do something for menu_sorting
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Dialog onCreateDialogForCurrenciesSelection(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.currency_selection_dialog_title)
               .setItems(currencies_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.swapAdapter(adapter, false);
        viewModel.isLoading().observe(getViewLifecycleOwner(), binding.refresher::setRefreshing);
        binding.refresher.setOnRefreshListener(viewModel::refresh);
    }

    @Override
    public void onDestroyView() {
        binding.recycler.swapAdapter(null, false);
        super.onDestroyView();
    }
}
