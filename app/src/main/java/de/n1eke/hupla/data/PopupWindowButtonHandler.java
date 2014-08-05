package de.n1eke.hupla.data;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;

import de.n1eke.hupla.ImageSelectedListener;
import de.n1eke.hupla.R;

/**
 * Created by michi on 04.08.14.
 */
public class PopupWindowButtonHandler implements View.OnClickListener{

    private PopupWindow popupWindow;
    private ImageSelectedListener imageSelectedListener;

    public PopupWindowButtonHandler(TableLayout tableLayout, PopupWindow popupWindow, ImageSelectedListener imageSelectedListener) {
        this.popupWindow = popupWindow;
        this.imageSelectedListener = imageSelectedListener;

        for(int i = 0; i<tableLayout.getChildCount();i++) {
            View possibleRow = tableLayout.getChildAt(i);
            if(possibleRow instanceof TableRow) {
                TableRow row = (TableRow) possibleRow;
                for(int j = 0; j < row.getChildCount(); j++) {
                    View possibleImageButton = row.getChildAt(j);
                    if(possibleImageButton instanceof ImageButton) {
                        ImageButton imageButton = (ImageButton) possibleImageButton;
                        imageButton.setOnClickListener(this);
                    } else if(possibleImageButton instanceof Button) {
                        Button button = (Button) possibleImageButton;
                        button.setOnClickListener(this);
                    }
                }
            }
        }
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        HuPlaType huPlaType;

        if(id == R.id.image_button_wolf) {
            huPlaType = HuPlaType.WOLF;
        } else if(id == R.id.image_button_heart) {
            huPlaType = HuPlaType.HEART;
        } else if(id == R.id.image_button_sheep) {
            huPlaType = HuPlaType.SHEEP;
        } else if(id == R.id.image_button_circle_blue) {
            huPlaType = HuPlaType.BLUE;
        } else if(id == R.id.image_button_circle_green) {
            huPlaType = HuPlaType.GREEN;
        } else if(id == R.id.image_button_circle_red) {
            huPlaType = HuPlaType.RED;
        } else if(id == R.id.image_button_circle_grey) {
            huPlaType = HuPlaType.GREY;
        } else if(id == R.id.image_button_circle_orange) {
            huPlaType = HuPlaType.ORANGE;
        } else if(id == R.id.image_button_circle_yellow) {
            huPlaType = HuPlaType.YELLOW;
        } else if(id == R.id.image_button_dog) {
            huPlaType = HuPlaType.DOG;
        } else if(id == R.id.image_button_na) {
            huPlaType = HuPlaType.NA;
        } else if(id == R.id.button_cancel) {
            huPlaType = null;
        }
        else {
            huPlaType = null;
        }

        imageSelectedListener.imageWasSelected(huPlaType);

        popupWindow.dismiss();
    }
}
