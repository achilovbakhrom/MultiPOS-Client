package com.jim.multipos.customView.recyclerView

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jim.multipos.R
import com.jim.multipos.core.BaseActions
import com.jim.multipos.customView.recyclerView.adapter.*
import kotlinx.android.synthetic.main.mp_recycler_view.view.*
import java.io.Serializable
import java.text.ParsePosition

class MPRecyclerView<T: Serializable>: FrameLayout {

    private var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    var adapter: BaseAdapter<T>? = null
        set(value) {
            field = value
            if (viewHolder == null) {
                throw Exception("Please setup view holder")
            }
            val items = adapter?.items
            if (selectionMode == SelectionModes.NONE) {
                field = SimpleAdapter(viewHolder!!)
            } else {
                field = SelectableAdapter(viewHolder!!)
                if (selectionMode == SelectionModes.SINGLE) {
                    (field as SelectableAdapter).mode = SelectionMode.SINGLE
                } else {
                    (field as SelectableAdapter).mode = SelectionMode.MULTIPLE
                }
            }
            if (items != null && !items.isEmpty()) {
                field?.setItems(list = items)
            }
            if (itemSelectionListener != null) {
                (field as SelectableAdapter).listener = itemSelectionListener
            }
            recyclerView?.adapter = field
            field = value
        }

    var listener: OnLoadMoreListener? = null

    var itemSelectionListener: BaseActions<in T>? = null
        set(value) {
            if (selectionMode == SelectionModes.NONE) {
                throw Exception("This mode is not selectable, change mode to Selectable mode")
            }
            if (adapter != null) {
                (adapter as SelectableAdapter).listener = value
            }
            field = value
        }

    var itemsCount = 0
        get() = adapter?.items?.size ?: 0


    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var visibleThreshold = 2

    var isLoading = false
        set(value) {
            swipeRefreshLayout?.isEnabled = !value
            field = value
        }

    var stopLoading = false
        set(value) {
            swipeRefreshLayout?.isEnabled = !value
            field = value
        }

    private var addingItems: MutableList<T> = mutableListOf()

    var viewHolder: BaseViewHolder<T>? = null


    var selectionMode: SelectionModes = SelectionModes.NONE
        set(value) {
            if (viewHolder == null) {
                throw Exception("Please setup view holder first")
            }

            val items = adapter?.items
            if (value == SelectionModes.NONE) {
                adapter = SimpleAdapter(viewHolder!!)
            } else {
                adapter = SelectableAdapter(viewHolder!!)
                if (value == SelectionModes.SINGLE) {
                    (adapter as SelectableAdapter).mode = SelectionMode.SINGLE
                } else {
                    (adapter as SelectableAdapter).mode = SelectionMode.MULTIPLE
                }
            }
            if (items != null && !items.isEmpty()) {
                adapter?.setItems(list = items)
            }
            recyclerView?.adapter = adapter
            field = value
        }


    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            recyclerView?.layoutManager = value
            this.selectionMode = SelectionModes.SINGLE
            if (value is GridLayoutManager) {
                value.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val item = adapter?.items?.get(position)
                        return if (item == null) {
                            value.spanCount
                        } else {
                            1
                        }
                    }
                }
            }

            field = value
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {

        LayoutInflater.from(context).inflate(R.layout.mp_recycler_view, this, true)

        recyclerView = findViewById(R.id.rvList)

        swipeRefreshLayout = findViewById(R.id.srSwipeRefresh)
        swipeRefreshLayout?.setOnRefreshListener {
            adapter?.clear()
            listener?.onRefresh(recyclerView!!)
            isLoading = true
        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    return
                }
                if (recyclerView?.layoutManager is LinearLayoutManager) {
                    val lm = recyclerView.layoutManager as LinearLayoutManager
                    visibleItemCount = recyclerView.childCount
                    totalItemCount = lm.itemCount
                    firstVisibleItem = lm.findFirstVisibleItemPosition()
                    if (!stopLoading && !isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                        listener?.onLoadMore(recyclerView)
                        Log.d("sss", "loading zone")
                        isLoading = true

                        onLoadMore()
                    }
                }
            }
        })
    }




    private fun onLoadMore() {
        recyclerView?.post {
            adapter?.addItem(null)
            recyclerView?.smoothScrollToPosition(adapter?.itemCount!! - 1)
        }
    }

    fun loadMoreComplete() {
        if (stopLoading) return
        (context as AppCompatActivity).runOnUiThread {
            adapter?.items?.remove(null)
            adapter?.addItems(addingItems)
            addingItems.clear()
            isLoading = false
        }
    }

    fun refreshComplete() {
        if (stopLoading) return
        swipeRefreshLayout?.isRefreshing = false
        isLoading = false
    }

    fun refresh() {
        adapter?.clear()
        stopLoading = false
        swipeRefreshLayout?.isRefreshing = true
    }

    interface OnLoadMoreListener {
        fun onLoadMore(recyclerView: RecyclerView)
        fun onRefresh(recyclerView: RecyclerView)
    }

    fun setItems(list: List<T>){
        adapter?.setItems(list)
    }

    fun setItem(item: T?, position: Int){
        adapter?.setItem(item, position)
    }

    fun addItems(list: List<T>) {
        stopLoading = list.isEmpty()
        if (isLoading) {
            addingItems = list.toMutableList()
        } else {
            adapter?.addItems(list)
        }
    }

    fun addItemsAt(list: List<T>, at: Int) {
        adapter?.addItemsAt(list, at)
    }

    fun addItemAt(item: T?, at: Int) {
        adapter?.addItemAt(item, at)
    }

    fun addItem(item: T?) {
        adapter?.addItem(item)
    }

    fun removeItem(item: T?) {
        adapter?.removeItem(item)
    }

    fun removeItem(position: Int){
        adapter?.removeItem(position)
    }

    fun removeItems(items: List<T?>) {
        adapter?.removeItems(items)
    }

    fun clear() {
        adapter?.clear()
    }

    fun unselect() {
        if (selectionMode != SelectionModes.NONE) {
            (adapter as SelectableAdapter<T>).unselect()
        }
    }

    fun setRVBackgroundColor(color: Int) {
        flMPRecyclerVlew.setBackgroundColor(color)
    }

}



enum class SelectionModes {
    NONE, SINGLE, MULTIPLE
}

@Suppress("UNCHECKED_CAST")
inline fun <T: Serializable, reified VH: BaseViewHolder<T>> provideViewHolder(context: Context) : BaseViewHolder<T>? {
    val clazz = Class.forName(VH::class.java.canonicalName)
    val constructor = clazz.getConstructor(View::class.java)
    val result = constructor.newInstance(View(context))
    return if (result is BaseViewHolder<*>)
        result as BaseViewHolder<T>
    else
        null
}
