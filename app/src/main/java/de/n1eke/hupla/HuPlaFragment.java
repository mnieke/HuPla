package de.n1eke.hupla;

import android.app.Fragment;
import android.view.View;
import android.widget.PopupWindow;

import de.n1eke.hupla.ImageSelectedListener;

/**
 * Created by michi on 05.08.14.
 */
public abstract class HuPlaFragment extends Fragment implements ImageSelectedListener, View.OnClickListener {

    protected boolean isPopupOpened = false;
    protected PopupWindow popupWindow;

    public boolean isPopupOpened() {
        return isPopupOpened;
    }

    public void setPopupOpened(boolean isPopupOpened) {
        this.isPopupOpened = isPopupOpened;
    }


    public void closePopup() {
        isPopupOpened = false;
        if(popupWindow != null && popupWindow.isShowing())
            popupWindow.dismiss();
    }
}
