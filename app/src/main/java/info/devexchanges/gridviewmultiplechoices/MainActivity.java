package info.devexchanges.gridviewmultiplechoices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridViewAdapter gridViewAdapter;
    private GridView gridView;
    private ArrayList<Integer> savedPositions = new ArrayList<Integer>();

    // Keep all Images in array
    private Integer[] mThumbIds = {
            R.drawable.automobiles,
            R.drawable.clothing,
            R.drawable.education,
            R.drawable.electronics,
            R.drawable.entertainment,
            R.drawable.fashion,
            R.drawable.food,
            R.drawable.furniture,
            R.drawable.jewelery,
            R.drawable.medical,
            R.drawable.realestate,
            R.drawable.travel
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid);
        gridViewAdapter = new GridViewAdapter(getApplicationContext(), mThumbIds);
        gridView.setAdapter(gridViewAdapter);
    }


    public class GridViewAdapter extends BaseAdapter {

        private Context c;
        private Integer[] mThumbIds;

        public GridViewAdapter(Context c, Integer[] imag) {
            this.c = c;
            this.mThumbIds = imag;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final Viewholder viewholder;
            if (convertView == null) {
                viewholder = new Viewholder();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_grid, null);
                viewholder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                viewholder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

                convertView.setTag(viewholder);
            } else {
                viewholder = (Viewholder) convertView.getTag();
            }

            viewholder.imageView.setImageResource(mThumbIds[position]);

            viewholder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (savedPositions.size() > 3 && savedPositions.size() == 4) {

                        Toast.makeText(getApplicationContext(), "Please Select 4-options", Toast.LENGTH_LONG).show();


                        if (savedPositions.size() == 4) {

                            // for insertion we need to have cross check of all elements which are present in array
                            int k = 0;

                            for (int counter = 0; counter <= savedPositions.size() - 1; counter++) {



                                //  STAGE-2.) COMPARSION
                                // HERE WE COMPARE GRID SELECTED POSITION WITH
                                if (savedPositions.get(counter) == position) {
                                    // if number already present
                                    //  STAGE-4.) DELETION
                                    // remove the position
                                    savedPositions.remove(counter);

                                    // we will bring the picture to normal stage
                                    v.setAlpha(1);


                                    if(viewholder.checkBox.getVisibility()==v.VISIBLE)
                                    {
                                        // if already visible
                                        viewholder.checkBox.setVisibility(View.INVISIBLE);
                                        viewholder.checkBox.setChecked(false);
                                    }
                                    else {
                                        // if not visible
                                        viewholder.checkBox.setVisibility(View.VISIBLE);
                                        viewholder.checkBox.setChecked(true);
                                    }


                                } else {
                                    k++;
                                    if (k == savedPositions.size()) {

                                        break;
                                    }

                                }
                            }

                        }


                    } else {
                        //  Toast.makeText(getApplicationContext(),"earlierarraysize"+savedPositions.size(),Toast.LENGTH_LONG).show();
                        if (savedPositions.size() == 0) {

                            savedPositions.add(position);


                            if(viewholder.checkBox.getVisibility()==v.VISIBLE)
                            {
                                // if already visible
                                viewholder.checkBox.setVisibility(View.INVISIBLE);
                                viewholder.checkBox.setChecked(false);
                            }
                            else {
                                // if not visible
                                viewholder.checkBox.setVisibility(View.VISIBLE);
                                viewholder.checkBox.setChecked(true);
                            }

                            //here we are making
                            v.setAlpha(0.5f);
                            //  v.animate().alphaBy(0.5f);
                            Toast.makeText(getApplicationContext(), "arraysize" + savedPositions.size(), Toast.LENGTH_LONG).show();

                        } else {
                            // HERE WE WRITE LOGIC FOR SINGLE CASE IF USER SELECTED SAME ICON [SELECTED/UNSELECTED]

                            if (savedPositions.size() == 1 && savedPositions.get(0) == position) {
                                savedPositions.clear();
                                v.setAlpha(1);

                                if(viewholder.checkBox.getVisibility()==v.VISIBLE)
                                {
                                    // if already visible
                                    viewholder.checkBox.setVisibility(View.INVISIBLE);
                                    viewholder.checkBox.setChecked(false);
                                }
                                else {
                                    // if not visible
                                    viewholder.checkBox.setVisibility(View.VISIBLE);
                                    viewholder.checkBox.setChecked(true);
                                }
                            }

                            int k = 0;

                            for (int counter = 0; counter <= savedPositions.size() - 1; counter++) {


                                //     Toast.makeText(getApplicationContext(), "entered" + savedPositions.size(), Toast.LENGTH_LONG).show();
                                //  STAGE-2.) COMPARSION
                                // HERE WE COMPARE GRID SELECTED POSITION WITH
                                if (savedPositions.get(counter) == position) {
                                    // if number already present
                                    //  STAGE-4.) DELETION
                                    // remove the position
                                    savedPositions.remove(counter);
                                    // we will bring the picture to normal stage
                                    v.setAlpha(1);

                                    if(viewholder.checkBox.getVisibility()==v.VISIBLE)
                                    {
                                        // if already visible
                                        viewholder.checkBox.setVisibility(View.INVISIBLE);
                                        viewholder.checkBox.setChecked(false);
                                    }
                                    else {
                                        // if not visible
                                        viewholder.checkBox.setVisibility(View.VISIBLE);
                                        viewholder.checkBox.setChecked(true);
                                    }


                                } else {
                                    k++;
                                    if (k == savedPositions.size()) {
                                        // STAGE-3.) INSERTION
                                        // add the position to array
                                        savedPositions.add(position);

                                        // blur the image
                                        v.setAlpha(0.5f);

                                        if(viewholder.checkBox.getVisibility()==v.VISIBLE)
                                        {
                                            // if already visible
                                            viewholder.checkBox.setVisibility(View.INVISIBLE);
                                            viewholder.checkBox.setChecked(false);
                                        }
                                        else {
                                            // if not visible
                                            viewholder.checkBox.setVisibility(View.VISIBLE);
                                            viewholder.checkBox.setChecked(true);
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                    }

                }
            });

            return convertView;
        }

        class Viewholder {
            CheckBox checkBox;
            ImageView imageView;
        }
    }
}
