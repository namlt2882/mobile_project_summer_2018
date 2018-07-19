package project.baonq.AddTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import project.baonq.menu.R;
import project.baonq.model.DaoSession;
import project.baonq.model.Ledger;
import project.baonq.model.LedgerDao;
import project.baonq.model.TransactionGroup;
import project.baonq.service.App;
import project.baonq.service.LedgerService;
import project.baonq.service.TransactionGroupService;

public class ExpenseFragment extends Fragment {
    public ExpenseFragment() {
        // Required empty public constructor
    }
    private DaoSession daoSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        final List<TransactionGroup> list = new TransactionGroupService(daoSession).getAll();

        View view = inflater.inflate(R.layout.fragment_expense,null);

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.linear_layout);
        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        for (int i = 0; i < list.size();i++)
            if (list.get(i).getTransaction_type() == 1)
        {
            Button txt = new Button(getActivity());
            txt.setText(list.get(i).getName());
            txt.setTextColor(Color.parseColor("#000000"));
            txt.setBackgroundColor(Color.TRANSPARENT);
            final int finalI = i;
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),AddTransaction.class);
                    intent.putExtra("catId",list.get(finalI).getId());
                    startActivity(intent);
                }
            });
            layout.addView(txt);
        }
        return view;
    }

}
