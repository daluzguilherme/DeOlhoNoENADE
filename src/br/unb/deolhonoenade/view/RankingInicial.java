package br.unb.deolhonoenade.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.unb.deolhonoenade.R;

public class RankingInicial extends Activity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	private Spinner tipUniv, spinnerEstados, spinnerCidades;
	private List<String> tipos = new ArrayList<String>();
	private String tipo, estado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking_inicial);
		
		TextView cursoSelecionado = (TextView) findViewById(R.id.textView1);
		cursoSelecionado.setText(getIntent().getExtras().getString("cursoSelecionado"));
		
		addItensOnSpinnerEstado();
		addListenerOnButtonBuscar();
		addItensOnSpinnerTipo();
		
		
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		} 
		// Set up the action bar to show a dropdown list.
/*		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section1),
								getString(R.string.title_section2),
								getString(R.string.title_section3), }), this);	*/
		
		
	}


	private void addItensOnSpinnerTipo() {
		
		//Adicionando dados do Spinner Tipo de Universidade
				tipos.add("Ambas");
				tipos.add("Privada");
				tipos.add("Publica");
				
				//Identificando o Spinner
				tipUniv = (Spinner) findViewById(R.id.universidade);
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
				ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
				spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				tipUniv.setAdapter(spinnerArrayAdapter);
		 
				//M�todo do Spinner para capturar o item selecionado
				tipUniv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		 
					@Override
					public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
						//pega nome pela posição
						tipo = parent.getItemAtPosition(posicao).toString();
						//imprime um Toast na tela com o nome que foi selecionado
						Toast.makeText(RankingInicial.this, "Op��o Selecionada: " + tipo, Toast.LENGTH_LONG).show();
					}
		 
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		
	}


	private void addListenerOnButtonBuscar() {

		//Botão Buscar
				Button buscar = (Button) findViewById(R.id.buscar);
				buscar.setOnClickListener (new OnClickListener(){
					
					@Override
			    	public void onClick(View v) {
			    		Intent intent = new Intent(RankingInicial.this, RankingResult.class);
			    		startActivity(intent);
			    	}
				});
		
	}


	private void addItensOnSpinnerEstado() {
		
		spinnerEstados = (Spinner) findViewById(R.id.SpinnerEstados);
		List<String> list = new ArrayList<String>();
		list.add("Acre");
		list.add("Alagoas");
		list.add("Amapa");
		list.add("Amazonas");
		list.add("Bahia");
		list.add("Ceara");
		list.add("Espirito Santo");
		list.add("Goias");
		list.add("Maranhao");
		list.add("Mato Grosso");
		list.add("Mato Drosso do Norte");
		list.add("Minas Gerais");
		list.add("Parana");
		list.add("Paraiba");
		list.add("Para");
		list.add("Pernambuco");
		list.add("Piaui");
		list.add("Rio de Janeiro");
		list.add("Rio Grande do Norte");
		list.add("Rio Grande do Sul");
		list.add("Maranhao");
		list.add("Rondonia");
		list.add("Roraima");
		list.add("Santa Catarina");
		list.add("Sergipe");
		list.add("Sao Paulo");
		list.add("Tocantins");
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerEstados.setAdapter(dataAdapter);
			
			
			
			spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		 
					@Override
					public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
						//pega nome pela posição
						estado = parent.getItemAtPosition(posicao).toString();
						//imprime um Toast na tela com o nome que foi selecionado
						Toast.makeText(RankingInicial.this, "Estado Selecionado: " + estado, Toast.LENGTH_LONG).show();
					}
		 
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
			
			
	}


	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
//		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
//				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking_inicial, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		getFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

/*		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_ranking_inicial,
					container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
	//		textView.setText(Integer.toString(getArguments().getInt(
	//				ARG_SECTION_NUMBER)));
			return rootView; 
		} */
	}

}
