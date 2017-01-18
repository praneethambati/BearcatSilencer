package com.example.praneethambati.bearcatsilencer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        displayListView();

        checkButtonClick();
    }
    private void displayListView() {

        //Array list of countries
        ArrayList<LocationsModel> locationsList = new ArrayList<LocationsModel>();
        LocationsModel locations = new LocationsModel("Colden Hall",94.555555,-30.222222,false);
        locationsList.add(locations);
        locations = new LocationsModel("Students Union",94.555555,-30.222222,false);
        locationsList.add(locations);
        locations = new LocationsModel("Library",94.555555,-30.222222,false);
        locationsList.add(locations);
        locations = new LocationsModel("Garret Strong",94.555555,-30.222222,false);
        locationsList.add(locations);


        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(LocationsActivity.this,R.layout.locations_info, locationsList);
        ListView listView = (ListView) findViewById(R.id.locationsListView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                LocationsModel locations = (LocationsModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + locations.getLocationName(),
                        Toast.LENGTH_LONG).show();
            }
        });
}


private class MyCustomAdapter extends ArrayAdapter<LocationsModel> {

    private ArrayList<LocationsModel> locationsList;

    public MyCustomAdapter(Context context, int textViewResourceId,
                           ArrayList<LocationsModel> locationList) {
        super(context, textViewResourceId, locationList);
        this.locationsList = new ArrayList<LocationsModel>();
        this.locationsList.addAll(locationList);
    }

    private class ViewHolder {
        TextView code;
        CheckBox name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.locations_info, null);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(holder);

            holder.name.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    LocationsModel country = (LocationsModel) cb.getTag();
                    Toast.makeText(getApplicationContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    country.setSelected(cb.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        LocationsModel location = locationsList.get(position);
        holder.code.setText(" (" +  location.getLat() + ")");
        holder.name.setText(location.getLocationName());
        holder.name.setChecked(location.isSelected());
        holder.name.setTag(location);

        return convertView;

    }

}

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.save);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<LocationsModel> countryList = dataAdapter.locationsList;
                for(int i=0;i<countryList.size();i++){
                    LocationsModel country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getLocationName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

                Intent i = new Intent();
                i.putExtra("locations", (Serializable) responseText);
                setResult(RESULT_OK, i);
                finish();

            }
        });

    }

}
