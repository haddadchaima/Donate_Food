package com.example.chaima.donatefood.Donater;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaima.donatefood.Annoncer.Needy;
import com.example.chaima.donatefood.Annoncer.NeedyRecyclerViewAdapter;
import com.example.chaima.donatefood.Annoncer.UpdateNeedyActivity;
import com.example.chaima.donatefood.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

class DonRecyclerViewAdapter extends
        RecyclerView.Adapter<DonRecyclerViewAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener{

    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;
    private final FragmentManager mFragment;
    private List<Don> donList;
    private Context context;
    private Don objectDon ;

    NeedyRecyclerViewAdapter needyRecyclerViewAdapter ;
    ArrayList<Needy> needyList ;

    DatabaseReference databaseReference ;



   

    public DonRecyclerViewAdapter(FragmentManager fragment, List<Don> list, Context ctx) {
        donList = list;
        context = ctx;
        mFragment  = fragment;
    }
    @Override
    public int getItemCount() {
        return donList.size();
    }

    @Override
    public DonRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_don, parent, false);
        objectDon = new Don();

        DonRecyclerViewAdapter.ViewHolder viewHolder =
                new DonRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final DonRecyclerViewAdapter.ViewHolder holder, final int position) {
        //final Context context = FragmentDonation.get ;
        final int itemPos = position;
        final Don don = donList.get(position);

        databaseReference = FirebaseDatabase.getInstance().getReference("Don");

        holder.txtViewTitle.setText(don.getTitle());
        holder.txtViewDesc.setText(don.getDescription());
        holder.txtViewHealthCondition.setText(don.getHealthCondition());
        holder.txtViewNumberPerson.setText("For :" + don.getNumberOfPerson() + " person");
        holder.txtViewTimePickUp.setText(don.getTimePickup());
        holder.txtViewAdr.setText("Place: " + don.getAdresse());
        /*Bitmap image = decodeFromFirebaseBase64(don.getImageUrl());
        holder.imgDon.setImageBitmap(image);*/

        if (don.getImageUrl()!=null&&!don.getImageUrl().contains("http")) {
            try {
                Bitmap image = decodeFromFirebaseBase64(don.getImageUrl());
                holder.imgDon.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
       } else {
            // This block of code should already exist, we're just moving it to the 'else' statement:
            Picasso.with(context)
                    .load(don.getImageUrl())
                    .fit().centerCrop()
                    .into(holder.imgDon);
        }

        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.btnMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_list);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                //handle menu1 click

                                Intent intentEditDon = new Intent(context, UpdateDonActivity.class);
                                intentEditDon.putExtra("id", don.getIdDon());
                                intentEditDon.putExtra("Tilte", don.getTitle());
                                intentEditDon.putExtra("Description", don.getDescription());
                                intentEditDon.putExtra("HealthConditon", don.getHealthCondition());
                                intentEditDon.putExtra("NumberOfPerson", don.getNumberOfPerson());
                                intentEditDon.putExtra("Place", don.getAdresse());
                                intentEditDon.putExtra("Time", don.getTimePickup());
                                intentEditDon.putExtra("Img", don.getImageUrl());

                                context.startActivity(intentEditDon);

                                break;
                            case R.id.menu_delete:
                                //  deleteClassifiedAd(classifiedAd.getAdId(), itemPos);
                                // Get the clicked item label
                                String idDon = donList.get(position).getIdDon();

                                // Remove the item on remove/button click
                                donList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,donList.size());

                                databaseReference.child(idDon).removeValue();
                                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

     /*holder.btnAffect.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            /* FragmentManager fragmentNeedy = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentNeedy.beginTransaction();

             SelectDonFragment fragment = new SelectDonFragment();
             fragmentTransaction.add(R.id.FrameLayout_select_needy, fragment);
             fragmentTransaction.commit();

         }

     }); */



       /* holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  deleteClassifiedAd(classifiedAd.getAdId(), itemPos);
                // Get the clicked item label

                String donId = donList.get(position).getIdDon();

                // Remove the item on remove/button click
                donList.remove(position);

                notifyItemRemoved(position);

                notifyItemRangeChanged(position,donList.size());

                // Show the removed item label
              //  Toast.makeText(mContext,"Removed : " + itemLabel,Toast.LENGTH_SHORT).show();

                databaseReference.child(donId).removeValue();
                Toast.makeText(view.getContext(),"Deleted",Toast.LENGTH_SHORT).show();
            }
        });*/



        /*holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                Don donPos = donList.get(position);

                dialog.setContentView(R.layout.dialog_update_don); //layout for dialog
                dialog.setTitle("Edit The Information about Don");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText title = (EditText) dialog.findViewById(R.id.ediTxtTitle);
                EditText desc = (EditText) dialog.findViewById(R.id.ediTxtDescription);
                EditText healthCond = (EditText) dialog.findViewById(R.id.ediTxtHealthCondition);
                EditText timePick = (EditText) dialog.findViewById(R.id.editTxtPickUpTime);
                Spinner spNumberPerson = (Spinner) dialog.findViewById(R.id.spinnerNumberPerson);
                EditText adr = (EditText) dialog.findViewById(R.id.ediTxtSetAdr);
                //Spinner nbrPerson = (Spinner) dialog.findViewById(R.id.spinnerNumberPerson);
                ImageView imgFood = (ImageView) dialog.findViewById(R.id.imageView);
                View btnDone = dialog.findViewById(R.id.btn_done_update);
                View btnCancel = dialog.findViewById(R.id.btn_cancel_update);


                spNumberPerson.setOnItemSelectedListener(DonRecyclerViewAdapter.this);

                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter;
                adapter = ArrayAdapter.createFromResource(context,
                        R.array.numberPerson, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spNumberPerson.setAdapter(adapter);

                //set handling event for 2 buttons and spinner
               // spNumberPerson.setOnItemSelectedListener(onItemSelectedListener());

                title.setText(donPos.getTitle());
                desc.setText(donPos.getDescription());
                healthCond.setText(donPos.getHealthCondition());
              //  objectDon.setNumberOfPerson(spNumberPerson.getOnItemClickListener().toString());
                timePick.setText(donPos.getTimePickup());
                adr.setText(donPos.getAdresse());
               String nbrP = spNumberPerson.getItemAtPosition(position).toString() ;

               // parent.getItemAtPosition(pos).toString();
              //  imgFood.setImageBitmap(donPos.getImageUrl());

                btnDone.setOnClickListener(onConfirmListener(position, title, desc, healthCond, timePick, adr, nbrP, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();

            }
        });
*/
       // mListener.onItemClicked(donList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView txtViewTitle, txtViewDesc, txtViewHealthCondition, txtViewNumberPerson, txtViewTimePickUp, txtViewAdr ,textViewDateCurrent, TxtViewTimeCurrent;

        ImageView imgDon ;
        ImageButton btnDelete, btnEdit, btnMenu, btnAffect ;

        public ViewHolder(View view) {
            super(view);

            txtViewTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtViewDesc = (TextView) view.findViewById(R.id.txtDesc);
            txtViewHealthCondition = (TextView) view.findViewById(R.id.txtHealthCondition);
            txtViewNumberPerson = (TextView) view.findViewById(R.id.txtNumberOfPerson);
            txtViewTimePickUp =(TextView) view.findViewById(R.id.txtTimePickUp);
            txtViewAdr= (TextView) view.findViewById(R.id.txtPlace);
            imgDon = (ImageView) view.findViewById(R.id.imgDon);
            btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
            btnAffect = (ImageButton) view.findViewById(R.id.btn_affect);

            btnAffect.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mFragment.beginTransaction().add(R.id.FrameLayout_select_needy,new SelectDonFragment()).commit();
        }

       /* @Override
        public void onClick(View view){
            mFragment.beginTransaction().replace(R.id.containerRow,new MyFragment()).commit();
        }*/
    }

  /*private void editClassifiedAd(String adId){
        FragmentManager fm = ((ClassifiedsActivity)context).getSupportFragmentManager();

        Bundle bundle=new Bundle();
        bundle.putString("adId", adId);

        AddClassifiedFragment addFragment = new AddClassifiedFragment();
        addFragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.adds_frame, addFragment).commit();
    }
    private void deleteClassifiedAd(String adId, final int position){
        FirebaseDatabase.getInstance().getReference()
                .child("classified").child(adId).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //remove item from list alos and refresh recyclerview
                            adsList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, adsList.size());

                            Log.d("Delete Ad", "Classified has been deleted");
                            Toast.makeText(context,
                                    "Classified has been deleted",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Delete Ad", "Classified couldn't be deleted");
                            Toast.makeText(context,
                                    "Classified could not be deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    public static Bitmap decodeFromFirebaseBase64(String image) throws  IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    private View.OnClickListener onConfirmListener(final int position, final EditText title, final EditText desc, final EditText health, final EditText timePick, final EditText adresse, final String numberPers, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Don donUpdated = new Don();

                Don donUpdated = new Don(donList.get(position).getIdDon(), title.toString() , desc.toString(), health.toString(), adresse.toString(), numberPers ,timePick.toString(), false);
                String idDon = donList.get(position).getIdDon();

                databaseReference.child(idDon).setValue(donUpdated);

                donList.remove(position);

                donList.add(position,donUpdated);

                dialog.dismiss();
                ArrayAdapter adapter = new ArrayAdapter(context, position);
                adapter.notifyDataSetChanged();

              /* //int pos = donUpdated.get(position);
              // adding new object to arraylist
               donUpdated.setTitle(title.getText().toString().trim());
               donUpdated.setDescription(desc.getText().toString().trim());
               donUpdated.setHealthCondition(health.getText().toString().trim());

               donList.remove(position);
               donList.add(donUpdated);
               notifyItemChanged(position);
               notifyDataSetChanged();

                //notify data set changed in RecyclerView adapter
                ArrayAdapter adapter = new ArrayAdapter(context, position);
                adapter.notifyDataSetChanged();

               // databaseReference = FirebaseDatabase.getInstance().getReference("Don");

                databaseReference = FirebaseDatabase.getInstance().getReference("Don");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                                databaseReference.setValue(donList.get(position));



                       *//* if (dataSnapshot.getValue() != null) {
                            Message newMessage = dataSnapshot.getValue(Message.class);
                            if (newMessage != null) {
                                for (int i = 0; i < myArrayList().size(); i++) {
                                    if (myArrayList().get(i).timestamp == newMessage.timestamp) {
                                        myArrayList().remove(i);
                                        myArrayList().add(i, newMessage);
                                        break;
                                    }
                                }
                            }
                            adapter.notifyDataSetChanged();*//*

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("User", databaseError.getMessage());
                    }*/
               // });
            }
        };
    }

    public View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }

}
