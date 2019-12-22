package com.monika.kotlinmvvmkoin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monika.kotlinmvvmkoin.R
import com.monika.kotlinmvvmkoin.adapters.FactsRecyclerAdapter
import com.monika.kotlinmvvmkoin.model.Facts
import com.monika.kotlinmvvmkoin.viewmodel.FactsViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_facts_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactsListFragment : Fragment() {

    private var factsList = arrayListOf<Facts>()

    private var disposable: Disposable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager

    val factsListViewModel by viewModel<FactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_facts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView(factsList)

        getFactsList()
    }

    private fun setUpRecyclerView(factsList : ArrayList<Facts>) {
        layoutManager = LinearLayoutManager(requireContext())
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = FactsRecyclerAdapter(requireContext(), factsList)
    }

    private fun getFactsList() {

        factsListViewModel.data.observe(this, Observer {
            getActivity()?.setTitle(it.title)
            setUpRecyclerView(it.getObjList())
        })

        factsListViewModel.loadingState.observe(this, Observer {
            /*LoadingState.Status.FAILED -> Toast.makeText(baseContext, it.msg, Toast.LENGTH_SHORT).show()
            LoadingState.Status.RUNNING -> Toast.makeText(baseContext, "Loading", Toast.LENGTH_SHORT).show()
            LoadingState.Status.SUCCESS -> Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()*/
        })
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}