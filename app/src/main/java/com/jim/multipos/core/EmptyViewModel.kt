package com.jim.multipos.core

import com.jim.multipos.core.managers.DataManager
import javax.inject.Inject

class EmptyViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager)