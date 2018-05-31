package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import TcdManagerApp.com.tctcd.tcdmanager.R;
import TcdManagerApp.com.tctcd.tcdmanager.tools.BmobTools;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private TextView username;
    private TextView detail;
    private Listfragment mlistfragment;
    private InvoiceFormatFragment invoiceFormatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //设置底部的那一个按钮以及绑定监听器
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //用drawerlayout 和 actionbardrawer 来实现侧滑效果
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //初始化fragment
        mlistfragment = new Listfragment();
        invoiceFormatFragment = new InvoiceFormatFragment();

        //为侧滑出来的布局的item注册监听器
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //默认选中餐补查询
        navigationView.setCheckedItem(R.id.meal_search);
        if (navigationView.getMenu().getItem(0).getSubMenu().getItem(0).isChecked()){
            setFragment(mlistfragment);
        }
        //如果是leader权限，则显示此餐补发票提交菜单
        if (BmobTools.userEntity.getPermission().equals("leader")){
            navigationView.getMenu().getItem(0).getSubMenu().getItem(1).setVisible(true);
        }



        //为侧滑出来的navigationview的上面的个人信息设置登陆用户text
        View headerview = navigationView.inflateHeaderView(R.layout.nav_header_main);
        username = headerview.findViewById(R.id.username);
        detail = headerview.findViewById(R.id.details);
        username.setText(BmobTools.userEntity.getName());
        detail.setText(BmobTools.userEntity.getTeam()+"-"+BmobTools.userEntity.getGroup());
    }

    public void setFragment(Listfragment fragment) {
        if(!fragment.isVisible()){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    public void setFragment(InvoiceFormatFragment fragment) {
        if(!fragment.isVisible()){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    //点击返回按钮的事件
    @Override
    public void onBackPressed() {
        //判断左边的是否拉出，如果是，则关闭，不是则不作处理
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

    //设置右上角三点图标菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //右上角菜单被选中的监听器
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.all_action:
                Toast.makeText(this, "app1", Toast.LENGTH_LONG).show();
                mlistfragment.queryPayAsGroup("Group","APP");
                mlistfragment.queryAsGroup("Group","");
                break;
            case R.id.app1_action:
                Toast.makeText(this, "app1", Toast.LENGTH_LONG).show();
                mlistfragment.queryPayAsGroup("Group","APP1");
                mlistfragment.queryAsGroup("Group","APP1");
                break;
            case R.id.app2_action:
                Toast.makeText(this, "app2", Toast.LENGTH_LONG).show();
                mlistfragment.queryPayAsGroup("Group","APP2");
                mlistfragment.queryAsGroup("Group","APP2");
                break;
            case R.id.app3_action:
                Toast.makeText(this, "app3", Toast.LENGTH_LONG).show();
                mlistfragment.queryPayAsGroup("Group","APP3");
                mlistfragment.queryAsGroup("Group","APP3");
                break;
            case R.id.app4_action:
                Toast.makeText(this, "app4", Toast.LENGTH_LONG).show();
                mlistfragment.queryPayAsGroup("Group","APP4");
                mlistfragment.queryAsGroup("Group","APP4");
                break;
        }

        return true;
    }


    //左边划开的菜单栏的item的监听器
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.invoice_format:
                setFragment(invoiceFormatFragment);
                break;
            case R.id.meal_search:
                setFragment(mlistfragment);
                break;
            case R.id.meal_commit:
                break;
//            case R.id.nav_slideshow:
//                break;
//            case R.id.nav_manage:
//                break;
//            case R.id.nav_share:
//                break;
//            case R.id.nav_send:
//                break;
        }
        //点击了某一个菜单之后关闭DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
