package itdp.flockcorp.duckuniversity.ui.addschedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddScheduleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddScheduleViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}