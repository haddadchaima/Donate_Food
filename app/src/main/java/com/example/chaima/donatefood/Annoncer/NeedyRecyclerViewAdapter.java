package com.example.chaima.donatefood.Annoncer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaima.donatefood.Annoncer.NeedyRecyclerViewAdapter;

import com.example.chaima.donatefood.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class NeedyRecyclerViewAdapter  extends
        RecyclerView.Adapter<NeedyRecyclerViewAdapter.ViewHolder> {

    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;
    private List<Needy> needyList;
    private Context context;
    DatabaseReference databaseReference ;

    public NeedyRecyclerViewAdapter(List<Needy> list, Context ctx) {
        needyList = list;
        context = ctx;
    }

    @Override
    public int getItemCount() {
        return needyList.size();
    }

    @Override
    public NeedyRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_needy, parent, false);

        NeedyRecyclerViewAdapter.ViewHolder viewHolder =
                new NeedyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final NeedyRecyclerViewAdapter.ViewHolder holder, final int position) {
        final int itemPos = position;
       final Needy needy = needyList.get(position);

        databaseReference = FirebaseDatabase.getInstance().getReference("Needy");

        holder.txtViewDesc.setText(needy.getDescription());
        holder.txtViewHealthCondition.setText(needy.getHealthConditionNeedy());
        holder.txtViewNumberPerson.setText("For :" + needy.getNumberOfPersonNeedy() + " person");
        holder.txtViewAdr.setText("Place: " + needy.getAdresseNeedy());

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

                                                              Intent intentEditNeedy = new Intent(context, UpdateNeedyActivity.class);
                                                              intentEditNeedy.putExtra("id", needy.getIdNeedy());
                                                              intentEditNeedy.putExtra("Description", needy.getDescription());
                                                              intentEditNeedy.putExtra("HealthConditon", needy.getHealthConditionNeedy());
                                                              intentEditNeedy.putExtra("NumberOfPerson", needy.getNumberOfPersonNeedy());
                                                              intentEditNeedy.putExtra("Place", needy.getAdresseNeedy());
                                                              intentEditNeedy.putExtra("Tel", needy.getTel());
                                                              intentEditNeedy.putExtra("Perid", needy.isPeriodically());
                                                              intentEditNeedy.putExtra("Temp", needy.isTemporary());
                                                              context.startActivity(intentEditNeedy);

                                                              break;
                                                          case R.id.menu_delete:
                                                              //  deleteClassifiedAd(classifiedAd.getAdId(), itemPos);
                                                              // Get the clicked item label
                                                              String idNeedy = needyList.get(position).getIdNeedy();

                                                              // Remove the item on remove/button click
                                                              needyList.remove(position);
                                                              notifyItemRemoved(position);
                                                              notifyItemRangeChanged(position,needyList.size());

                                                              databaseReference.child(idNeedy).removeValue();
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




    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtViewDesc, txtViewHealthCondition, txtViewNumberPerson, txtViewTimePickUp, txtViewAdr ,textViewDateCurrent, TxtViewTimeCurrent;

        ImageButton btnDelete, btnEdit, btnMenu ;

        public ViewHolder(View view) {
            super(view);


            txtViewDesc = (TextView) view.findViewById(R.id.txtDesc);
            txtViewHealthCondition = (TextView) view.findViewById(R.id.txtHealthCondition);
            txtViewNumberPerson = (TextView) view.findViewById(R.id.txtNumberOfPerson);
            txtViewAdr= (TextView) view.findViewById(R.id.txtPlace);

            btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
          /*  btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
            btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);*/

        }
    }



    }

