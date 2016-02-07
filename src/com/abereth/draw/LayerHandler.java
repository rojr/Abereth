package com.abereth.draw;

import com.abereth.exception.RedundantDestination;
import com.abereth.game.View;
import com.abereth.objects.living.Living;

import java.util.ArrayList;
import java.util.Collections;

public class LayerHandler
{
	private int size;
	private ArrayList<Layer> layers;
	private ArrayList<Integer> layerMappings; //Used for efficiency. Not particularly a fan of sorting anything while
											  //while the game is running, so keep everything pre-sorted

	public LayerHandler()
	{
		layers = new ArrayList<>( );
		layerMappings = new ArrayList<>(  );
		layerMappings.add( 0 );
		layers.add( new Layer( 0 ) );
		this.size = 0;
	}

	public void add( Drawable drawable )
	{

		for( Layer layer : layers)
		{
			if( drawable.getLayer() == layer.getLayer() )
			{
				layer.add( drawable );
				size++;
				return;
			}
		}

		Layer l = new Layer( drawable.getLayer() );
		l.add( drawable );
		size++;
		layers.add( l );

		updateLayerMappings();
	}

	private void updateLayerMappings()
	{
		layerMappings.clear();
		for( Layer l : layers )
		{
			layerMappings.add( l.getLayer() );
		}
		Collections.sort( layerMappings );
		//Collections.reverse( layerMappings );
	}

	public void remove( Drawable d )
	{
		layers.get( d.getLayer() ).remove( d );
		size--;
	}

	public int size()
	{
		return this.size;
	}

	public void render( View view, int delta )
	{
		for( int i : layerMappings )
		{
			try
			{
				Layer l = getMappedLayer( i );
				if( l != null )
				{
					l.render( view, delta );
				}
			}
			catch ( IndexOutOfBoundsException ex )
			{
				ex.printStackTrace();
			}
		}
	}

	private Layer getMappedLayer( int layerID )
	{
		for( Layer l : layers )
		{
			if( l.getLayer() == layerID )
			{
				return l;
			}
		}
		return null;
	}

	/**
	 * CAREFUL WHEN CALLING THIS METHOD
	 * Must be called BEFORE setting the layer
	 * on the drawable object, as the current
	 * layer will be taken as reference
	 * on where to look for in the old layer
	 * to literally take it out of that layer and move
	 * it to the next layer. So just a little FYI
	 * @param d
	 * @param destination
	 */
	public void moveObject( Drawable d, int destination )
	{
		if( d.getLayer() == destination )
		{
			try
			{
				throw new RedundantDestination( "Layer destination is the same as current layer... Are you sure you're calling this method before setting the layer?" );
			}
			catch ( Exception ex){}
		}

		layers.get( d.getLayer() ).remove( d );
		try
		{
			layers.get( destination ).add( d );
		}
		catch ( IndexOutOfBoundsException ex )
		{
			Layer l = new Layer( destination );
			l.add( d );
			size++;
			layers.add( l );
			updateLayerMappings();
		}
	}

	private class Layer
	{

		private int id;
		private ArrayList<Drawable> drawables;
		public Layer( int id )
		{
			this.id = id;
			this.drawables = new ArrayList<>(  );
		}

		public void add( Drawable... d )
		{
			Collections.addAll( drawables, d );
		}

		public void remove( Drawable... d )
		{
			for( Drawable drawable : d )
			{
				drawables.remove( d );
			}
		}

		public void render( View view, int delta )
		{
			for( Drawable d : drawables )
			{
				if(d instanceof Living )
				{
					( ( Living ) d ).onUpdate( delta );
				}
				view.getGame().getDraw().render( d, view );
			}
		}

		public int getLayer()
		{
			return this.id;
		}
	}
}
