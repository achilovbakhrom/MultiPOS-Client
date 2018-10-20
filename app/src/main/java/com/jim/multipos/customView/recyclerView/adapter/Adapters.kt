package com.jim.multipos.customView.recyclerView.adapter

import java.io.Serializable


class SimpleAdapter<T: Serializable>(viewHolder: BaseViewHolder<T>): BaseAdapter<T>(viewHolder)

class SelectableAdapter<T: Serializable>(viewHolder: BaseViewHolder<T>): BaseSelectableAdapter<T>(viewHolder)
