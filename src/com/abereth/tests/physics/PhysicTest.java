package com.abereth.tests.physics;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.shapes.Square;
import com.abereth.event.TimedEvent;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.input.Keyboard;
import com.abereth.input.Mouse;
import com.abereth.objects.living.Physical;
import com.abereth.objects.living.PhysicalBox;
import com.abereth.world.World;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.MouseJoint;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Vector2;

import java.util.function.Consumer;

/**
 * Created by sanic on 12/12/2014.
 */
public class PhysicTest extends View
{
	private World world;
	private PhysicalBox box;
	private MouseJoint mouse;
	public static void main( String[] args )
	{
		Game g = new Game( );
		g.addView( new PhysicTest( g ) );
		g.Start();
	}

	@Override
	public void Initialize()
	{
		super.Initialize();

	}

	public PhysicTest( Game game )
	{
		super( game );
		this.world = new World();
		this.SetWorld( world );
		world.setGravity( new Vector2( 0, 16 ) );
		this.box = new PhysicalBox( 60, 20, 50, 50 );
		box.setColor( Color.BLUE );
		world.add( box );
		PhysicalBox solidGround = new PhysicalBox( 0, G.HEIGHT - 40, G.WIDTH * 2, 40 );
		solidGround.getBody().setMassType( Mass.Type.INFINITE );
		solidGround.getBody().getFixtures().forEach( new Consumer<BodyFixture>()
		{
			@Override
			public void accept( BodyFixture bodyFixture )
			{
				bodyFixture.setRestitution( 2 );
			}
		} );
		world.add( solidGround );

		Physical left = new PhysicalBox( 0, 0, 20, G.HEIGHT * 2 );
		left.getBody().setMassType( Mass.Type.INFINITE );
		world.add( left );

		Physical right = new PhysicalBox( G.WIDTH - 20, 0, 20, G.HEIGHT * 2 );
		right.getBody().setMassType( Mass.Type.INFINITE );
		world.add( right );
		mouse =  new MouseJoint( box.getBody(), new Vector2( 0 , 0 ), 1122, 1, 456345);
		mouse.setCollisionAllowed( true );
		world.getPhysicalWorld().addJoint( mouse );
		box.getBody().getFixtures().forEach( new Consumer<BodyFixture>()
		{
			@Override
			public void accept( BodyFixture bodyFixture )
			{
				bodyFixture.setDensity( 4321 );
			}
		} );

		world.setGravity( new Vector2( 0, 65 ) );

		GetEventManager().add( new TimedEvent<View>()
		{
			@Override
			public void init( View view )
			{
				super.init( view );
				SetInterval( 2 );
			}

			@Override
			public void EachInterval( int delta, View view )
			{
				PhysicalBox box = new PhysicalBox( Math.random() * G.WIDTH * 2, 0, 5, 5 );
				box.getBody().getFixtures().forEach( new Consumer<BodyFixture>()
				{
					@Override
					public void accept( BodyFixture bodyFixture )
					{
						bodyFixture.setDensity( 1 );
					}
				} );
				box.setColor( Color.random() );
				world.add( box );
			}

			@Override
			public boolean isDone( View view )
			{
				return false;
			}
		}, true );
	}

	private int rotated = 0;
	@Override
	public void update( int delta )
	{
		//box.setTexture( TextureLoader.createTexture( "res/icon16.png" ) );

		mouse.setTarget( new Vector2( Mouse.getX() / G.DYN4J_PIXELS_TO_METERS, Mouse.getY() / G.DYN4J_PIXELS_TO_METERS ) );
		if( Mouse.isLeftMouseClicked() )
		{
			box.getBody().getTransform().setRotation( rotated );
			rotated += 7;
		}
		if( Keyboard.isKeyDown( Keyboard.Key.Space ) )
		{
			box.getBody().applyForce( new Vector2( 0, -50 ) );
		}

		if( Keyboard.isKeyDown( Keyboard.Key.D ) )
		{
			box.getBody().applyForce( new Vector2( 21, 0 ) );
		}
		if( Keyboard.isKeyDown( Keyboard.Key.A ) )
		{
			box.getBody().applyForce( new Vector2( -21, 0 ) );
		}
		if( Mouse.isRightMouseDown() )
		{
			add( new Square( Mouse.getX(), Mouse.getY(), 50, 50 ) );
		}
	}
}
