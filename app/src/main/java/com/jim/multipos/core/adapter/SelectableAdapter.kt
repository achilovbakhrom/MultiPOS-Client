package com.jim.multipos.core.adapter

import com.jim.multipos.core.BaseActions
import java.io.Serializable


abstract class SelectableAdapter<T: Serializable, VH: BaseViewHolder<T>>: BaseAdapter<T, VH>() {

    var listener: BaseActions<T>? = null

    var selectedPosition = -1
    var selectedItem: T? = null
        get() {
            if (getMode() == SelectionMode.MULTIPLE) {
                throw Exception("Mode is MULTIPLE, but you are trying to get SINGLE mode element")
            }
            return if (selectedPosition == -1 || getMode() == SelectionMode.MULTIPLE) {
                null
            } else {
                items[selectedPosition]
            }
        }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView?.setOnClickListener {
            onBeginingSelection(viewHodler = holder, lastSelection = selectedPosition)
            if (getMode() == SelectionMode.MULTIPLE) {
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position)
                } else {
                    selectedPositions.add(position)
                }
            }
            if (getMode() == SelectionMode.SINGLE) {
                selectedPosition = position
            }
            if (position < items.size) {
                when(getMode()) {
                    SelectionMode.SINGLE -> {
                        onSingleModeItemSelected(item = items[position], position = selectedPosition, viewHodler = holder)
                    }
                    SelectionMode.MULTIPLE -> {
                        onMultipleModeitemSelected(selecteditems, selectedPositions, viewHodler = holder)
                    }
                }
            }

        }
        holder.itemView?.setOnLongClickListener {
            if (position < items.size) {
                onItemLongClicked(items[position], position, holder)
            }
            true
        }
        if (getMode() == SelectionMode.SINGLE) {
            holder.onBind(item = items[position], position = position, isSelected = selectedPosition == position)
        } else {
            holder.onBind(item = items[position], position = position, isSelected = selectedPositions.contains(position))
        }
    }


    var selectedPositions = mutableListOf<Int>()
    var selecteditems = mutableListOf<T>()
        get() {
            if (getMode() == SelectionMode.SINGLE) {
                throw Exception("Mode is SINGLE, but you are trying to get MULTIPLE mode elements")
            }
            return if (selectedPositions.isEmpty())
                mutableListOf()
            else {
                val temp = mutableListOf<T>()
                for (position in selectedPositions) {
                    temp.add(items[position])
                }
                temp
            }
        }

    open internal fun onBeginingSelection(viewHodler: VH, lastSelection: Int) {}
    open internal fun onSingleModeItemSelected(item: T, position: Int, viewHodler: VH) {
        listener?.onItemClick(item, position)
    }
    open internal fun onMultipleModeitemSelected(items: List<T>, positions: List<Int>, viewHodler: VH) {}
    open internal fun onItemLongClicked(item: T, position: Int, viewHodler: VH) {
        listener?.onItemLongClick(item, position)
    }

    open internal fun getMode(): SelectionMode = SelectionMode.SINGLE

    /**
     *  Removing by position
     */
    fun removeByPosition(position: Int) {
        if (getMode() == SelectionMode.SINGLE) {
            selectedPosition = if (selectedPosition == position) {
                -1
            }  else {
                selectedPosition
            }

        } else {
            selectedPositions.remove(position)
        }
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     *  Removing by item
     */
    fun removeItem(item: T) {
        val removingPosition = items.indexOf(item)
        if (removingPosition >= 0) {
            if (getMode() == SelectionMode.SINGLE) {
                selectedPosition = if (selectedPosition == removingPosition) {
                    -1
                } else {
                    selectedPosition
                }
            } else {
                selectedPositions.remove(removingPosition)
            }
            items.remove(item)
            notifyItemRemoved(removingPosition)
        }
    }


    /**
     *  Remove all selected position items
     *  Depends on chosen mode:
     *      - if mode is SINGLE, will be removed a selected position
     *      - if mode is MULTIPLE, will be removed items at all selected positions
     */
    fun removeBySelectedPosition() {
        if (getMode() == SelectionMode.SINGLE) {
            if (selectedPosition != -1) {
                selectedPosition = -1
                items.removeAt(selectedPosition)
            }
            notifyItemRemoved(selectedPosition)
        } else {
            if (!selectedPositions.isEmpty()) {
                for (position in selectedPositions) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
                selectedPositions.clear()
            }
        }
    }
}

enum class SelectionMode {
    SINGLE, MULTIPLE
}
