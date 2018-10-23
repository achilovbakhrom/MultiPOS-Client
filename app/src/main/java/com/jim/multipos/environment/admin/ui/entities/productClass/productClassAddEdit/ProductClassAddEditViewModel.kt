package com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit

import com.jim.multipos.core.fragments.baseAddEditFragment.BaseAddEditViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import javax.inject.Inject

class ProductClassAddEditViewModel @Inject constructor(mDataManager: DataManager): BaseAddEditViewModel<ProductClass>(mDataManager) {

}