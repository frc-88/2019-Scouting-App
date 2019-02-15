import android.widget.ImageView;
import android.widget.TextView;

public class IngameElement {

    public int pos;
    public ImageView im;
    public TextView sandstormFlag;
    public TextView rocketNumber;
    public TextView sandstormNumber;

    public IngameElement(int pos , ImageView im , TextView ssFlag , TextView rn , TextView sn){
        this.pos = pos;
        this.im = im;
        this.sandstormFlag = ssFlag;
        this.rocketNumber = rn;
        this.sandstormNumber = sn;
    }
}
