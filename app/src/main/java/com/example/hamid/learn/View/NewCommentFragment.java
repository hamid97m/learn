package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.hamid.learn.Api.NewCommentApi;
import com.example.hamid.learn.R;

import static com.example.hamid.learn.View.DetailOfLocation.fragmentManager;

/**
 * Created by Hamid on 8/19/2017.
 */

public class NewCommentFragment extends Fragment {
    private View view;
    private  EditText edt_name;
    private  EditText edt_dis;

    private  String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.new_comment_layout_fragment,container,false);
         edt_name=(EditText)view.findViewById(R.id.edt_name);
         edt_dis=(EditText)view.findViewById(R.id.edt_dis);


        Button btn_send_comment=(Button)view.findViewById(R.id.btn_send_comment);
        final RatingBar ratingBar=(RatingBar)view.findViewById(R.id.ratingBar);
        btn_send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_dis.getText().toString().length() > 1) {
                    NewCommentApi newCommentApi = new NewCommentApi();
                    if (edt_name.getText().length() != 0) {
                        name = edt_name.getText().toString();
                    } else name = "ناشناس";
                    newCommentApi.addcoment(name,
                            edt_dis.getText().toString(),
                            ratingBar.getRating(),
                            DetailOfLocation.id);
                    edt_dis.setText("");
                    edt_name.setText("");
                    Toast.makeText(getContext(), "نظر شما با موفقیت ارسال شد و پس از بازبینی نمایش داده میشود" + "  امتیاز شما :" + ratingBar.getRating(), Toast.LENGTH_LONG).show();
                    FragmentTransaction replace = fragmentManager.beginTransaction();
                    replace.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    replace.replace(R.id.fragmenrcontainer, DetailOfLocation.commentsFragment);
                    replace.addToBackStack(null);
                    replace.commit();
                }
                else{
                    Toast.makeText(getContext(),"لطفا نظر خود را بنویسید",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
