package com.draconra.moviekotlin.home.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseFragment
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.home.search.adapter.SearchResultsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search_movies.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : BaseFragment(), TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchSubject.onNext(s.toString())
    }

    @Inject
    lateinit var factory: SearchVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchResultAdapter: SearchResultsAdapter
    private lateinit var searchSubject: PublishSubject<String>
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieApp().createSearchComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        searchSubject = PublishSubject.create()

        //TODO: Handle screen rotation during debounce
        val disposable = searchSubject.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it != searchResultAdapter.query) {
                        viewModel.search(it)
                    } else {
//                        Log.i(javaClass.simpleName, "Same query -> aborting search")
                    }
                }

        compositeDisposable.add(disposable)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                showToast(it.message ?: "")
            }
        })
    }

    private fun handleViewState(state: SearchViewState) {
        searchMoviesProgress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        val movies = state.movies ?: listOf()
        if (state.showNoResultsMessage) {
            searchMoviesNoResultsMessage.visibility = View.VISIBLE
            searchMoviesNoResultsMessage.text = String.format(
                    getString(R.string.search_no_results_message,
                            state.lastSearchedQuery))
        } else {
            searchMoviesNoResultsMessage.visibility = View.GONE
        }
        searchResultAdapter.setResults(movies, state.lastSearchedQuery)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_search_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMoviesEditText.addTextChangedListener(this)
        searchResultAdapter = SearchResultsAdapter(imageLoader) { movie, movieView ->
            showSoftKeyboard(false)
            navigateToMovieDetailsScreen(movie)
        }
        recyclerView = searchMoviesRecyclerview as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = searchResultAdapter
        searchMoviesEditText.requestFocus()
        showSoftKeyboard(true)
    }

    private fun showSoftKeyboard(show: Boolean) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } else {
            imm.hideSoftInputFromWindow(searchMoviesEditText.windowToken, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("lastSearch", searchMoviesEditText.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showSoftKeyboard(false)
        compositeDisposable.clear()
        getMovieApp().releaseSearchComponent()
    }

}