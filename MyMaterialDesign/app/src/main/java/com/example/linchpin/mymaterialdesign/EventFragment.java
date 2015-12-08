package com.example.linchpin.mymaterialdesign;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EventFragment extends Fragment {


    public EventFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*((MainActivity) getActivity()).toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                else
                {
//                    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_event, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ContactAdapter ca = new ContactAdapter(createList(30));
        recList.setAdapter(ca);

        return rootView;
    }

    private List<ContactInfo> createList(int size) {

        List<ContactInfo> result = new ArrayList<ContactInfo>();
        for (int i=1; i <= size; i++) {
            ContactInfo ci = new ContactInfo();
            ci.name = ContactInfo.NAME_PREFIX + i;
            ci.surname = ContactInfo.SURNAME_PREFIX + i;
            ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }


    class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

        private List<ContactInfo> contactList;

        public ContactAdapter(List<ContactInfo> contactList) {
            this.contactList = contactList;
        }


        @Override
        public int getItemCount() {
            return contactList.size();
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
            ContactInfo ci = contactList.get(i);
            contactViewHolder.vName.setText(ci.name);
            contactViewHolder.vSurname.setText(ci.surname);
            contactViewHolder.vEmail.setText(ci.email);
            contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_layout, viewGroup, false);



            return new ContactViewHolder(itemView);
        }

        public class ContactViewHolder extends RecyclerView.ViewHolder {

            protected TextView vName;
            protected TextView vSurname;
            protected TextView vEmail;
            protected TextView vTitle;

            public ContactViewHolder(View v) {
                super(v);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "card layout click event " + getPosition(), Toast.LENGTH_SHORT).show();


                        Fragment fragment=new BlankFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

//                        ActionBar actionBar =  ((MainActivity) getActivity()).getSupportActionBar();
//                        actionBar.setDisplayHomeAsUpEnabled(true);
//                        ((MainActivity) getActivity()).actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                        ((MainActivity) getActivity()).animateToBackArrow();

                    }
                });

                vName =  (TextView) v.findViewById(R.id.txtName);
                vSurname = (TextView)  v.findViewById(R.id.txtSurname);
                vEmail = (TextView)  v.findViewById(R.id.txtEmail);
                vTitle = (TextView) v.findViewById(R.id.title);
            }
        }
    }

    private class ContactInfo {
        protected String name;
        protected String surname;
        protected String email;


        protected static final String NAME_PREFIX = "Name_";
        protected static final String SURNAME_PREFIX = "Surname_";
        protected static final String EMAIL_PREFIX = "email_";
    }


}
