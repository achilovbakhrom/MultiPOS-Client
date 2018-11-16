package com.jim.multipos.environment.admin.ui.company.right.addEdit.about

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.customView.MpEditText
import com.jim.multipos.databinding.AboutCompanyFragmentBinding
import com.jim.multipos.environment.admin.model.AboutInformation
import com.jim.multipos.utils.FragmentCommunicationOperations
import com.jim.multipos.utils.saveImageToUri
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.about_company_fragment.*
import kotlinx.android.synthetic.main.contact_data_layout.*
import javax.inject.Inject

class AboutCompanyFragment : BaseFragment<AboutCompanyFragmentBinding, AboutCompanyViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val CAMERA_REQUEST = 1888
    private val STORAGE_REQUEST = 1999
    private val CAMERA_PERMISSION_CODE = 100
    private var counter = 0

    private var typeList: ArrayList<Int> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            mViewModel?.aboutInformation = arguments?.getSerializable("aboutInformation") as? AboutInformation
        }
        mViewModel?.onViewCreated()

        if (savedInstanceState?.getIntegerArrayList("typeList") != null) {
            typeList = savedInstanceState.getIntegerArrayList("typeList")
        }
        if (savedInstanceState?.getInt("counter") != null) {
            counter = savedInstanceState.getInt("counter")
        }

        btnCompanyAddEditCancel.setOnClickListener {
            mViewModel?.cancelAdding()
        }
        btnAddContactData.setOnClickListener {
            tvContactDataError.text = ""
            showContactDialog()
        }
        btnCompanyNext.setOnClickListener {
            mViewModel?.aboutInformation?.onNextAction = true
            mViewModel?.deliverDataToMainClass()
        }
        etCompanyName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                etCompanyName.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etCompanyBusinessOccupation.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                etCompanyBusinessOccupation.error = null
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        rlCompanyPhoto.setOnClickListener {
            openPhotoDialog()
        }
        initObservers()
    }

    private fun initObservers() {
        mViewModel?.companyNameErrorAction?.observe(this, Observer {
            etCompanyName.error = it
        })

        mViewModel?.businessOccupationErrorAction?.observe(this, Observer {
            etCompanyBusinessOccupation.error = it
        })

        mViewModel?.cancelAction?.observe(this, Observer {
            if(mViewModel?.aboutInformation?.editMode!!) {
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, null)
            }else
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.CANCEL.operation, null)
        })

        mViewModel?.setAboutAction?.observe(this, Observer {
            mViewModel?.aboutInformation?.companyName = etCompanyName.text.toString()
            mViewModel?.aboutInformation?.companyOccupation = etCompanyBusinessOccupation.text.toString()
            mViewModel?.aboutInformation?.description = etDescription.text.toString()
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, mViewModel?.aboutInformation)
        })

        mViewModel?.fillContentAction?.observe(this, Observer {
            val aboutInformation = mViewModel?.aboutInformation
            etCompanyName.setText(aboutInformation?.companyName)
            etCompanyBusinessOccupation.setText(aboutInformation?.companyOccupation)
            mViewModel?.aboutInformation?.onNextAction = false
            if (aboutInformation?.contactData?.isEmpty() == false) {
                for (contactData in aboutInformation.contactData!!) {
                    addContactDataRow(contactData.type, contactData.data)
                }
            }
            if (aboutInformation?.imagePath != null) {
                val photo = BitmapFactory.decodeFile(aboutInformation.imagePath)
                ivCompanyPhoto.setImageDrawable(null)
                ivCompanyPhoto.setImageBitmap(photo)
                ivCompanyPhotoPlus.visibility = View.GONE

            }
            etDescription.setText(aboutInformation?.description)
        })
    }


    private fun openPhotoDialog() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.take_photo_dialog)
        dialog.findViewById<TextView>(R.id.btnFromCamera).setOnClickListener {
            if (context?.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                if (context?.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA),
                            CAMERA_PERMISSION_CODE)
                } else {
                    val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST)
                }
            } else {
                ActivityCompat.requestPermissions(context as AppCompatActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_REQUEST)
            }
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.btnFromGallery).setOnClickListener {

        }
        dialog.setCancelable(true)
        dialog.show()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("typeList", typeList)
        outState.putInt("counter", counter)
    }


    private fun showContactDialog() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.contact_data_dialog_layout)
        dialog.window.setLayout(context?.resources?.displayMetrics?.widthPixels ?: 0 * 0.3.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.findViewById<Button>(R.id.btnPhone).setOnClickListener {
            addContactDataRow(0)
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.btnEmail).setOnClickListener {
            addContactDataRow(1)
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun addContactDataRow(type: Int, text: String = "") {
        val title = if (type == 0) { getString(R.string.phone)} else {getString(R.string.email)}
        typeList.add(type)
        val row = LayoutInflater.from(context).inflate(R.layout.contact_data_layout, null, false)
        row.findViewById<TextView>(R.id.tvContactDataTypeName).text = title
        row.findViewById<MpEditText>(R.id.etContactData).tag = counter
        row.findViewById<MpEditText>(R.id.etContactData).setText(text)
        if(type == 0)
            row.findViewById<MpEditText>(R.id.etContactData).inputType = InputType.TYPE_CLASS_NUMBER

        row.tag = counter
        mViewModel?.addNewContactData(counter, type, text)
        row.findViewById<MpEditText>(R.id.etContactData).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val tag = row.findViewById<MpEditText>(R.id.etContactData).tag as Int
                if(etContactData.text.isEmpty())
                    etContactData.error = "Empty"
                else
                    mViewModel?.contactDataTextChanged(tag, typeList[tag], p0.toString())
            }
        })

        row.findViewById<ImageView>(R.id.btnRemoveContactData).setOnClickListener {
            llContactDataContainer.removeView(row)
            mViewModel?.removeContactData(row.tag as Int)
            counter--
        }

        llContactDataContainer.addView(row)
        counter++
    }

    fun checkUIValidation():Boolean{
        var isValid = true
        if(etCompanyName.text.isEmpty()) {
            etCompanyName.error = getString(R.string.company_name_required)
            isValid = false
        }
        if(etCompanyBusinessOccupation.text.isEmpty()){
            etCompanyBusinessOccupation.error = getString(R.string.business_occupation_required)
            isValid = false
        }
        if(mViewModel?.contactData!=null&&mViewModel?.contactData?.size!!>0){
        }else {
            tvContactDataError.text = "Contact data is not set"
            isValid = false
        }

        return isValid
    }

    fun deliverDataToMainClass() {
        mViewModel?.deliverDataToMainClass()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_REQUEST && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "storage permission denied", Toast.LENGTH_LONG).show()
            return
        }
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(context, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.about_company_fragment
    override fun getViewModel(): AboutCompanyViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(AboutCompanyViewModel::class.java)
        return mViewModel as AboutCompanyViewModel
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                ivCompanyPhoto.setImageDrawable(null)
                ivCompanyPhoto.setImageBitmap(BitmapFactory.decodeFile(result.uri.path))
                ivCompanyPhotoPlus.visibility = View.GONE
                mViewModel?.setImageLocalPath(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Toast.makeText(context!!, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            val photo = data?.extras?.get("data") as? Bitmap
            if (photo != null) {
                val file = saveImageToUri(photo, context!!)
                CropImage.activity(Uri.fromFile(file)).start(context as AppCompatActivity)
            } else {
                ivCompanyPhoto.setImageResource(R.drawable.ic_company_photo)
                ivCompanyPhotoPlus.visibility = View.VISIBLE
            }
        }
    }
}
