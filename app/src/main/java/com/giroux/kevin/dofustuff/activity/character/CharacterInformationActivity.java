package com.giroux.kevin.dofustuff.activity.character;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.giroux.kevin.dofustuff.BuildConfig;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.character.fragment.ArmesFragment;
import com.giroux.kevin.dofustuff.activity.character.fragment.CaracteristicFragment;
import com.giroux.kevin.dofustuff.activity.character.fragment.DetailsFragment;
import com.giroux.kevin.dofustuff.activity.character.fragment.ItemFragment;
import com.giroux.kevin.dofustuff.activity.character.fragment.SortFragment;

public class CharacterInformationActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int[] tabIcons;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        FragmentManager fm = getSupportFragmentManager();

        long idCharacter = this.getIntent().getLongExtra("idCharacter",0);
        mSectionsPagerAdapter = new SectionsPagerAdapter(fm, idCharacter);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabIcons = new int[]{R.drawable.details, R.drawable.shield, R.drawable.caracteristics, R.drawable.magic, R.drawable.sword};
        for(int i = 0; i<tabLayout.getTabCount(); i++){
            if(i<tabIcons.length &&  tabLayout.getTabAt(i) != null){
                tabLayout.getTabAt(i).setIcon(tabIcons[i]);
                tabLayout.getTabAt(i).setText("");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_information, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static final String ARG_ID_CHARACTER = "id_character";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
           // args.putInt(ARG_ID_CHARACTER,)
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_character_information, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private long idCharacter;


        public SectionsPagerAdapter(FragmentManager fm, long idCharacter) {
            super(fm);
            this.idCharacter = idCharacter;

        }

        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();
            bundle.putLong("idCharacter", idCharacter);
            Fragment fr;

            switch (position){
                case 0 :
                    fr =  DetailsFragment.newInstance();
                    break;
                case 1 :
                    fr = ItemFragment.newInstance();
                    break;
                case 2 :
                    fr =  CaracteristicFragment.newInstance();
                    break;
                case 3 :
                    fr =  SortFragment.newInstance();
                    break;
                case 4 :
                    fr =  ArmesFragment.newInstance();
                    break;
                default :
                    fr =  DetailsFragment.newInstance();
                    break;
            }
            fr.setArguments(bundle);
            return fr;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Equipments";
                case 2:
                    return "Caracteristics";
                case 3 :
                    return "Sorts";
                case 4 :
                    return "Arms";
                default:
                    return "Details";
            }
        }
    }
}
