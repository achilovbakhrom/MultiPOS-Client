package com.jim.multipos.customView.recyclerView.adapter

import com.jim.multipos.core.BaseActions
import java.io.Serializable


abstract class BaseSelectableAdapter<T: Serializable>(viewHolder: BaseViewHolder<T>): BaseAdapter<T>(viewHolder) {

    var listener: BaseActions<in T>? = null

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


    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.itemView?.setOnClickListener {
            holder.onBeginSelection(lastSelection = selectedPosition)
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
                        holder.onSingleModeItemSelected(item = items[position], position = selectedPosition)
                        listener?.onItemClick(item = items[position], position = selectedPosition)
                    }
                    SelectionMode.MULTIPLE -> {
                        holder.onMultipleModeItemSelected(selectedItems, selectedPositions)
                    }
                }
            }
            notifyDataSetChanged()
        }
        holder.itemView?.setOnLongClickListener {
            if (position < items.size) {
                holder.onItemLongClicked(items[position], position)
                listener?.onItemLongClick(items[position], position)
            }
            notifyDataSetChanged()
            true

        }
        if (getMode() == SelectionMode.SINGLE) {
            holder.onBind(item = items[position], position = position, isSelected = selectedPosition == position)
        } else {
            holder.onBind(item = items[position], position = position, isSelected = selectedPositions.contains(position))
        }
    }

    fun unselect() {
        if (getMode() == SelectionMode.SINGLE) {
            selectedPosition = -1
            selectedItem = null
        } else {
            selectedPositions.clear()
            selectedItems.clear()
        }
        notifyDataSetChanged()
    }

    var selectedPositions = mutableListOf<Int>()
    var selectedItems = mutableListOf<T?>()
        get() {
            if (getMode() == SelectionMode.SINGLE) {
                throw Exception("Mode is SINGLE, but you are trying to get MULTIPLE MODE SELECTION elements")
            }
            return if (selectedPositions.isEmpty())
                mutableListOf()
            else {
                val temp = mutableListOf<T?>()
                for (position in selectedPositions) {
                    temp.add(items[position])
                }
                temp
            }
        }

    var mode: SelectionMode = SelectionMode.SINGLE

    internal open fun getMode(): SelectionMode = mode

    /**
     *  Remove all selected position items
     *  Depends on chosen mode:
     *      - if mode is SINGLE, will be removed a selected position
     *      - if mode is MULTIPLE, will be removed items at all selected positions
     */
    fun removeBySelectedPosition() {
        if (getMode() == SelectionMode.SINGLE) {
            if (selectedPosition != -1) {
                items.removeAt(selectedPosition)
                notifyItemRemoved(selectedPosition)
                selectedPosition = -1
            }
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
