package project.baonq.AddTransaction;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import project.baonq.menu.R;
import project.baonq.model.DaoSession;
import project.baonq.model.Ledger;
import project.baonq.model.Transaction;
import project.baonq.service.App;
import project.baonq.service.LedgerService;
import project.baonq.service.TransactionService;
import project.baonq.ui.AddLedgeActivity;
import project.baonq.ui.LedgeChoosenActivity;
import project.baonq.util.ConvertUtil;

public class ChooseLedger extends AppCompatActivity {
    private final static int LAYOUT_INFO = 1;
    private final static int LAYOUT_UPDATE = 2;

    private Ledger ledger;
    private DaoSession daoSession;
    private Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ledger);
        daoSession = ((App) getApplication()).getDaoSession();
        application = getApplication();
        //set init for action bar
        initActionBar();
        //set init for menu action
        initMenuAction();
        getLedgerData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAYOUT_INFO || requestCode == LAYOUT_UPDATE) {
            if (resultCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void loadDataFromSessionDao() {
        List<Ledger> ledgerList = getLedgerList();
        double totalLedgerCash = 0;
        String currency = "";
        //in case this is not have any ledger
        if (ledgerList != null) {
            currency = ConvertUtil.convertCurrency(ledgerList.get(0).getCurrency());
        }

        for (Ledger ledger : ledgerList) {
            createNewRowData(ledger, 0);
        }
    }


    private double sumOfTransaction(List<Transaction> transactionList) {
        double sum = 0;
        for (Transaction item : transactionList) {
            sum += item.getBalance();
        }
        return sum;
    }

    private List<Ledger> getLedgerList() {
        daoSession = ((App) getApplication()).getDaoSession();
        List<Ledger> ledgerList = new LedgerService(application).getAll();
        return ledgerList;
    }


    private void createImageButton(final Ledger ledger, final double sum, View submitLayout) {
        ImageButton imageButton = new ImageButton(this);
        imageButton.setImageResource(R.drawable.ic_edit_black_24dp);
        LinearLayout.LayoutParams imageButtonParam = new LinearLayout.LayoutParams(0, 45);
        imageButtonParam.weight = 0.1f;
        imageButtonParam.setMargins(0, 15, 0, 0);
        imageButton.setLayoutParams(imageButtonParam);
        imageButton.setBackground(getResources().getDrawable(R.color.colorWhite));
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLedger.this, AddLedgeActivity.class);
                intent.putExtra("id", ledger.getId());
                intent.putExtra("name", ledger.getName());
                intent.putExtra("currency", ledger.getCurrency());
                intent.putExtra("currentBalance", sum);
                startActivityForResult(intent, LAYOUT_UPDATE);
            }
        });
        LinearLayout submitLayOutLinear = submitLayout.findViewById(R.id.container);
        submitLayOutLinear.addView(imageButton);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View customView = layoutInflater.inflate(R.layout.ledge_choosen_sub_layout, null);
        actionBar.setCustomView(customView);
    }

    private void initMenuAction() {
        TextView txtClose = (TextView) findViewById(R.id.closeLedge);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getLedgerData() {
        daoSession = ((App) getApplication()).getDaoSession();

        List<Ledger> ledgerList = new LedgerService(application).getAll();

        for (Ledger ledger : ledgerList) {
            createNewRowData(ledger, 0);
        }

    }


    private void createNewRowData(final Ledger ledger, double sum) {
        View submitLayout = getLayoutInflater().inflate(R.layout.add_ledge_submit_layout, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 2, 0, 0);
        submitLayout.setLayoutParams(layoutParams);
        TextView txtTitle = submitLayout.findViewById(R.id.txtTittle);
        TextView txtCash = submitLayout.findViewById(R.id.txtCash);
        txtTitle.setText(ledger.getName());
        String currentBalanceFormat = ConvertUtil.convertCashFormat(sum);
        txtCash.setText(currentBalanceFormat + ConvertUtil.convertCurrency(ledger.getCurrency()));
        submitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLedger.this,AddTransaction.class);
                intent.putExtra("LedgerId",ledger.getId());
                startActivity(intent);
            }
        });
        //create image button

        LinearLayout contentLedgeChosenLayout = (LinearLayout) findViewById(R.id.abcd);
        Log.i("test", String.valueOf(contentLedgeChosenLayout));
        contentLedgeChosenLayout.addView(submitLayout);
    }
}
