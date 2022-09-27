package itdp.flockcorp.duckuniversity.ui.deleteschedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeleteScheduleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DeleteScheduleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}