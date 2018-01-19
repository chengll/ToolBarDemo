package test.dmdfchina.com.rxjavamvp;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.balysv.materialmenu.MaterialMenuDrawable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    private DrawerLayout mDrawerLayout;
    /**
     * 用来给Toolbar设置NavigationIcon，实现icon的动画切换
     */
    private MaterialMenuDrawable mMenuDrawable;
    private boolean isDrawerOpen;
    /**
     * icon的不同切换状态
     */
    private MaterialMenuDrawable.AnimationState mCurrentState;
    private View mStateView;
    private Button burger_arrow_button;
    private Button burger_x_button;
    private Button burger_check_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*toolbar.inflateMenu(R.menu.munu_layout);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle("导航栏");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));*/
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mStateView = findViewById(R.id.state_view);
        mMenuDrawable = new MaterialMenuDrawable(this, getResources().getColor(R.color.colorPrimary), MaterialMenuDrawable.Stroke.EXTRA_THIN);
        /**
         * 默认切换动画是从菜单icon到返回icon
         */
        mCurrentState = MaterialMenuDrawable.AnimationState.BURGER_ARROW;
        initToolBar();
        initDrawerLayout();
        initButton();

    }

    private void initButton() {
        burger_arrow_button = (Button) findViewById(R.id.burger_arrow_button);
        burger_x_button = (Button) findViewById(R.id.burger_x_button);
        burger_check_button = (Button) findViewById(R.id.burger_check_button);
        burger_arrow_button.setOnClickListener(this);
        burger_x_button.setOnClickListener(this);
        burger_check_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.burger_arrow_button:
                mCurrentState=MaterialMenuDrawable.AnimationState.BURGER_ARROW;
                break;
            case R.id.burger_x_button:
                mCurrentState=MaterialMenuDrawable.AnimationState.BURGER_X;
                break;
            case R.id.burger_check_button:
                mCurrentState=MaterialMenuDrawable.AnimationState.BURGER_CHECK;
                break;
            default:
                break;
        }
    }

    /*给抽屉添加事件*/
    private void initDrawerLayout() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                /**
                 * 实现drawerLayout滑动过程中icon的动画变化
                 */
                mMenuDrawable.setTransformationOffset(mCurrentState, isDrawerOpen ? 2 - slideOffset : slideOffset);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initToolBar() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(mMenuDrawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断抽屉是开的还是关的
                boolean isDraw = mDrawerLayout.isDrawerOpen(GravityCompat.START);
                if (isDraw) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return;
                }
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


}
