package ds.sample.viewmodel

import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import ds.prodigy.IComponent
import ds.prodigy.Presenter
import ds.sample.adapter.ViewPagerAdapter

class ViewPagerPresenter() : Presenter<IComponent>() {

    val adapter = ObservableField<ViewPagerAdapter>()

    override fun onCreate(bundle: Bundle?) {
        val a = component?.getActivity()
        a as FragmentActivity
        adapter.set(ViewPagerAdapter(a))
    }

}

