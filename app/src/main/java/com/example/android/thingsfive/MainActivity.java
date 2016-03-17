package com.example.android.thingsfive;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity{

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText nameField =  (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        CheckBox knowedgeCheckBox = (CheckBox) findViewById(R.id.add_knowedge);
        CheckBox designCheckBox = (CheckBox) findViewById(R.id.add_design);


        Boolean hasknowedge = knowedgeCheckBox.isChecked();
        Boolean hasDesign = designCheckBox.isChecked();

        Log.v("MainActivity", "Has Wipped Cream: " + hasknowedge);
        Log.v("MainActivity", "Has Wipped Cream: " + hasDesign);


        int price = calculatePrice();
        String priceMessage = createOrderSummary(name,price, hasknowedge, hasDesign);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        // only email app should handle this
        intent.putExtra(intent.EXTRA_SUBJECT, "Feed Back of: " + name);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);

    }

    // make buttons,  called xml when the  button is clicked.

    public void sendemail (View view){
        setContentView(R.layout.email);
    }

    public void moneys (View view){

        setContentView(R.layout.money1);
    }

    public void ideas (View view){

        setContentView(R.layout.pg1);
    }

    public void backmoney (View view){

        setContentView(R.layout.activity_main);
    }


    public void houses (View view){

        setContentView(R.layout.house1);
    }


    public void cars (View view){

        setContentView(R.layout.car1);
    }

    public void friends (View view){

        setContentView(R.layout.friends1);
    }


    /**
     * Calculates the price of the order.
     *
     * @param //quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {


        return quantity;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffes) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffes);

    }

    public void increment(View view) {
        if(quantity == 10){
            //Show an error message
            Toast.makeText(this, "You cannot rate more of 10", Toast.LENGTH_SHORT).show();
            //Exit this method early because there´s nothing left to do
            return;
        } quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            //Show an error message
            Toast.makeText(this, "You cannot rate less than 1", Toast.LENGTH_SHORT).show();
            //Exit this method early because there´s nothing left to do
            return;
        } quantity = quantity - 1;
        displayQuantity(quantity);
    }

    private String createOrderSummary(String name, int price, Boolean hasknowedge, Boolean hasDesign){

        String priceMessage = "" + name;
        priceMessage += "\nuseful? " + hasknowedge;
        priceMessage += "\nLike Design? " + hasDesign;
        priceMessage += "\nApp Grade : " + quantity ;
        priceMessage += "\nThanks for Use!";
        return priceMessage;
    }

}