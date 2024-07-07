package mobi.upod.timedurationpicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * Base class for implementing a time duration picker dialog fragment as described in the
 * <a href="https://developer.android.com/guide/topics/ui/controls/pickers.html">Pickers</a> guide in the
 * android documentation.
 * <p>
 * You need to implement #onDurationSet in your derived class. You can override #getInitialDuration if you want to
 * provide an initial duration to be set when the dialog starts.
 *
 * @see TimeDurationPickerDialog
 * @see TimeDurationPicker
 */
public abstract class TimeDurationPickerDialogFragment
    extends DialogFragment implements TimeDurationPickerDialog.OnDurationSetListener {

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = requireContext();

        final View view = getLayoutInflater().inflate(R.layout.time_duration_picker_dialog, null);
        TimeDurationPicker durationInputView = (TimeDurationPicker) view;

        final MaterialAlertDialogBuilder builder =
            new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton(
                    context.getString(android.R.string.ok),
                    (dialog, which) -> {
                        onDurationSet(durationInputView, durationInputView.getDuration());
                    }
                )
                .setNegativeButton(
                    context.getString(android.R.string.cancel),
                    (dialog, which) -> {}
                );

        return builder.create();
    }

    /**
     * The duration to be shown as default value when the dialog appears.
     *
     * @return the default duration in milliseconds.
     */
    protected long getInitialDuration() {
        return 0;
    }

    protected int setTimeUnits() {
        return TimeDurationPicker.HH_MM_SS;
    }
}
