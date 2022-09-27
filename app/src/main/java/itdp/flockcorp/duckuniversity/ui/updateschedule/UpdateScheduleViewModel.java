package itdp.flockcorp.duckuniversity.ui.updateschedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpdateScheduleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UpdateScheduleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}