package br.unb.deolhonoenade.view;

import java.util.ArrayList;
import java.util.List;

import br.unb.deolhonoenade.R;
import br.unb.deolhonoenade.R.id;
import br.unb.deolhonoenade.R.layout;
import br.unb.deolhonoenade.R.menu;
import br.unb.deolhonoenade.controller.ControllerCurso;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RankingResult extends Activity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private String uf, municipio, tipo, curso;
	private int codCurso;
	private ControllerCurso controller;
	private List<String> cursos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking_result);


		//tags: Curso Selecionado -> CodigoCurso
		//      Estado Selecionado -> Estado
		//		Cidade Selecionada -> Municipio
		//		Tipo Univercidade -> Tipo
		
		
		
		cursos = new ArrayList<String>();
		
		controller = new ControllerCurso(this);
		
		curso = getIntent().getExtras().getString("CodigoCurso");
		
		codCurso = controller.buscaCodCurso(curso);
		
		uf = getIntent().getExtras().getString("Estado");
		municipio = getIntent().getExtras().getString("Municipio");
		tipo = getIntent().getExtras().getString("Tipo");
		
		int tipoInt;
		if(tipo.equalsIgnoreCase("Ambas")){
			tipoInt = 0;
		}else if(tipo.equalsIgnoreCase("Privada")){
			tipoInt = 1;
		}else{
			tipoInt = 2;
		}
		
		if(municipio.equalsIgnoreCase("Todas")){
			if(tipo.equalsIgnoreCase("Ambas")){
				this.getStringCurso(codCurso, uf);
			}else{
				this.getStringCurso(codCurso, uf, tipoInt);
			}
		}else{
			if(tipo.equalsIgnoreCase("Ambas")){
				this.getStringCurso(codCurso, uf, municipio);
			}else{
				this.getStringCurso(codCurso, uf, municipio, tipo);
			}
		}
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, cursos);
		
		ListView myListView = (ListView) findViewById(R.id.listResult);
		
		myListView.setAdapter(dataAdapter);

		//C�digo abaixo dando erro de Null Pointer Exception.
		
		/*// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
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
								getString(R.string.title_section3), }), this); */
	}

	private void getStringCurso(int codCurso2, String uf2, int tipoInt) {
		cursos = controller.buscaStringCurso(codCurso2, uf2, tipoInt);
		
	}

	private void getStringCurso(int codCurso2, String uf2) {
		cursos = controller.buscaStringCurso(codCurso2, uf2);
		
	}

	private void getStringCurso(int codCurso2, String uf2, String municipio2,
			String tipo2) {
		cursos = controller.buscaStringCurso(codCurso2, uf2, municipio2, tipo2);
		
	}

	private void getStringCurso(int codCurso2, String uf2, String municipio2) {
		cursos = controller.buscaStringCurso(codCurso2, uf2, municipio2);
		
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
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking_result, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_ranking_result,
					container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		} */
	}

}
