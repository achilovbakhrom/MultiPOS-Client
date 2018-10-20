package com.jim.multipos.environment.admin.ui.signup.fragment.info

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.customView.MpCheckbox
import com.jim.multipos.databinding.AdminSignupInfoFragmentLayoutBinding
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.environment.admin.model.SignUp
import com.jim.multipos.environment.admin.ui.signup.SignUpViewModel
import com.jim.multipos.environment.admin.ui.signup.model.SignUpModel
import kotlinx.android.synthetic.main.admin_signup_info_fragment_layout.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class InfoFragment: BaseFragment<AdminSignupInfoFragmentLayoutBinding, SignUpViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var myCalendar = Calendar.getInstance()
    private var country: String?=null
    var pass: String?=null
    var mail: String?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_info_fragment_layout
    }

    override fun getViewModel(): SignUpViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(SignUpViewModel::class.java)
        return mViewModel as SignUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        mail = arguments?.getString("email")
        pass = arguments?.getString("pass")
        setUp()
    }

    private fun setUp() {
        val spinnerArray = arrayOf("a","b","c")
        country = spinnerArray[0]
        spinnerCountry.setItems(spinnerArray)
        spinnerCountry.setItemSelected { country = spinnerArray[it] }

        maleCheckBox.listener = object : MpCheckbox.CheckedChangeListener{
            override fun onCheckedChange(isChecked: Boolean) {
                if(isChecked)
                    femaleCheckBox.setChecked(false)
            }

        }

        femaleCheckBox.listener = object : MpCheckbox.CheckedChangeListener{
            override fun onCheckedChange(isChecked: Boolean) {
                if(isChecked)
                   maleCheckBox.setChecked(false)
            }

        }

        val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        etDatePicker.keyListener = null
        etDatePicker.setOnClickListener { v ->
            DatePickerDialog(context!!, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        backBtn.setOnClickListener { activity!!.onBackPressed() }

        nextBtn.setOnClickListener { mViewModel!!.sendInfo(SignUp(mail!!, pass!!,
                etFirstName.text.toString(), etLastName.text.toString()))}



    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        etDatePicker.setText(sdf.format(myCalendar.time))
    }
}