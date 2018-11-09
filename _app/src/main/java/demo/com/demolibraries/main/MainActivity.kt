package demo.com.demolibraries.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import demo.com.demolibraries.R
import demo.com.demolibraries.Utility

class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        printInfo()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        val rvListMenus = findViewById<RecyclerView>(R.id.rv_list_menu)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvListMenus.layoutManager = gridLayoutManager
        val menuAdapter = MenuAdapter(MENU, this)
        rvListMenus.adapter = menuAdapter
    }

    private fun printInfo() {
        Utility.hashFromSHA1("D5:00:6F:2E:44:50:95:16:CB:B0:57:45:ED:DF:DA:31:87:0A:29:09")
    }

    private fun openActivity(activity: Class<*>?) {
        startActivity(Intent(this, activity))
    }

    /*--------------------------OnItemClickListener------------------------*/
    override fun onItemClick(menu: MenuItem) {
        openActivity(menu.getActivityToOpen())
    }
}
