package com.example.linchpin.mymaterialdesign;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    int ICONS[] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    static Toolbar toolbar;
    static DrawerLayout drawerLayout; // drawer
    RecyclerView recyclerView;
    static ActionBarDrawerToggle actionBarDrawerToggle;

    private static final float MENU_POSITION = 0f;
    private static final float ARROW_POSITION = 1.0f;

    /*ListView listView;
    SwipeRefreshLayout mRefreshLayout;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


       /* if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.ic_home);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter=new MyAdapter(TITLES,ICONS,this);
        recyclerView.setAdapter(adapter);
        /*final GestureDetector mGestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    drawerLayout.closeDrawers();
                    Toast.makeText(MainActivity.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();

                    return true;

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });*/
        actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        /*listView=(ListView)findViewById(R.id.listView);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter mAdapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                 SnackbarManager.show(
                                Snackbar.with(getApplicationContext()) // context
                                        .text("Remove item?") // text to display
                                        .actionLabel("Ok") // action button label
                                        .actionListener(new ActionClickListener() {
                                            @Override
                                            public void onActionClicked(Snackbar snackbar) {
                                                Log.d("Snackbar click", "Undoing something");
                                                view.animate().setDuration(2000).alpha(0)
                                                        .withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                list.remove(item);
                                                                mAdapter.notifyDataSetChanged();
                                                                view.setAlpha(1);
                                                            }
                                                        });
                                            }
                                        }) // action button's ActionClickListener
                                , MainActivity.this); // activity where it is displayed

            }

        });

        mRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"Swipe Refresh Layout...",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                },3000);

            }
        });*/

        /*getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int stackHeight = getSupportFragmentManager().getBackStackEntryCount();
                if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                    getSupportActionBar().setHomeButtonEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);
                }
            }

        });*/


       /* if (fm.getBackStackEntryCount() > 0) {
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        }
        else
        {
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        }*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                    fm.popBackStack();
                    drawerLayout.closeDrawers();
                    drawerLayout.setDrawerListener(actionBarDrawerToggle);
                    animateToMenu();
                }
                else
                {
                    drawerLayout.openDrawer(GravityCompat.START);
                    drawerLayout.setDrawerListener(actionBarDrawerToggle);
                    animateToBackArrow();
                }
            }
        });

    }

    public static void animateToBackArrow() { //animation
        ValueAnimator anim = ValueAnimator.ofFloat(MENU_POSITION, ARROW_POSITION);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                actionBarDrawerToggle.onDrawerSlide(drawerLayout, slideOffset);
            }
        });

        anim.setInterpolator(new DecelerateInterpolator());
//        anim.setDuration(400);
        anim.start();

    }

    public static void animateToMenu() {
        ValueAnimator anim = ValueAnimator.ofFloat(ARROW_POSITION, MENU_POSITION);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                actionBarDrawerToggle.onDrawerSlide(drawerLayout, slideOffset);
            }
        });

        anim.setInterpolator(new DecelerateInterpolator());
//        anim.setDuration(400);
        anim.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            fm.popBackStack();
            animateToBackArrow();
        }
        else
        {
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            animateToMenu();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String mNavTitles[];
        private int mIcons[];

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        Context context;

        public MyAdapter(String[] titles, int[] icons, Context context) {
            mNavTitles=titles;
            mIcons=icons;
            this.context=context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem;

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            if(holder.holderId ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
        }

        @Override
        public int getItemCount() {
            return mNavTitles.length+1;
        }
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            int holderId;
            TextView textView;
            ImageView imageView;


            public ViewHolder(View itemView,int viewType) {
                super(itemView);
                itemView.setClickable(true);
                displayView(1);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();
                        displayView(getPosition());
                        MainActivity.drawerLayout.closeDrawers();
                    }
                });
                if(viewType==TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText);
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    holderId=1;
                }
                else {
                    holderId=0;
                }
            }

            private void displayView(int position) {
                Fragment fragment = null;
                String title = getString(R.string.app_name);
                switch (position) {
                    case 1:
                        fragment = new HomeFragment();
                        title = getString(R.string.title_home);
                        break;
                    case 2:
                        fragment = new EventFragment();
                        title = getString(R.string.title_event);
                        break;
                    case 3:
                        fragment = new HomeFragment();
                        title = getString(R.string.title_home);
                        break;
                    default:
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();

                    // set the toolbar title
                    getSupportActionBar().setTitle(title);
                    drawerLayout.closeDrawers();
                }
            }
        }

    }

    /*private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }*/



    /*@Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }*/

    /*@Override
    public void onBackPressed() {
        Fragment blankFragment=new EventFragment();
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.container_body);
        if(currentFragment == blankFragment) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        super.onBackPressed();
    }*/


}

