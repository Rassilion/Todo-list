package com.fregram.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdi on 7.09.2016.
 */
public class MainScreen extends Fragment {

    List<String> toDoListStrings;
    ListView toDoList ;
    EditText mainText;
    ArrayAdapter<String> toDoListAdapter;
    public MainScreen(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.main_screen,container,false);

        toDoList = (ListView) view.findViewById(R.id.to_do_list);
        toDoListStrings = new ArrayList<String>();
        toDoListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                toDoListStrings);

        mainText = (EditText) view.findViewById(R.id.main_text);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mainText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    String text = mainText.getText().toString();
                    if (text.length() > 0)
                    {
                        toDoListStrings.add(text);
                        mainText.setText("");
                        toDoList.setAdapter(toDoListAdapter);
                        handled = true;
                    }
                }
                return handled;
            }
        });

        return view;
    }
}
