package com.abereth.tests.physics;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.shapes.Square;
import com.abereth.game.Camera;
import com.abereth.game.Game;
import com.abereth.view.PhysicsWorldView;
import com.abereth.input.Keyboard;
import com.abereth.input.Mouse;
import com.abereth.objects.living.Physical;
import com.abereth.objects.living.PhysicalBox;
import com.abereth.world.PhysicsWorld;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.MouseJoint;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Vector2;

import java.util.function.Consumer;

/**
 * Created by sanic on 12/12/2014.
 */
public class PhysicTest extends PhysicsWorldView
{
	private PhysicsWorld world;
	private PhysicalBox box;
	private MouseJoint mouse;
	public static void main( String[] args )
	{
		Game g = new Game( 500, 500 );
		g.addView( new PhysicTest( g ) );
		g.start ();
	}

	@Override
	public void initialize()
	{
		super.initialize();

	}

	@Override
	public void onKill() {
		super.onKill();
	}

	public PhysicTest( Game game )
	{
		super( game );
		this.world = new PhysicsWorld ();
		this.setWorld(world);
		Square sqe = new Square ( 0, 0, G.WIDTH, G.HEIGHT );
		sqe.setColor ( Color.BLACK );
		add ( sqe );
		world.getPhysicalWorld ().setGravity ( new Vector2 ( 0, 16 ) );
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
				bodyFixture.setRestitution( 0 );
			}
		} );
		world.add( solidGround );

		Physical left = new PhysicalBox( 0, 0, 20, G.HEIGHT * 2 );
		left.getBody().setMassType( Mass.Type.INFINITE );
		world.add( left );

		Physical right = new PhysicalBox( G.WIDTH - 20, 0, 20, G.HEIGHT * 2 );
		right.getBody().setMassType( Mass.Type.INFINITE );
		world.add( right );
		mouse =  new MouseJoint( box.getBody(), new Vector2( 0 , 0 ), 1122, 1, 999999999 );
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

		world.getPhysicalWorld ().setGravity ( new Vector2 ( 0, 65 ) );

		Camera c = new Camera ( this, G.WIDTH - 200, G.HEIGHT - 200, 200, 200 );
		this.add ( c );
	}

	@Override
	public void update( int delta )
	{
		super.update ( delta );
		mouse.setTarget( new Vector2( this.getMouseX () / G.DYN4J_PIXELS_TO_METERS, this.getMouseY () / G.DYN4J_PIXELS_TO_METERS ) );
		if( Mouse.isLeftMouseDown() )
		{
			for( int i = 0; i < 2; i++ )
			{

				PhysicalBox box = new PhysicalBox ( this.getMouseX (), this.getMouseY (), Math.random () * 15 + 20, Math.random () * 15 + 20 );
				box.getBody ().getFixtures ().forEach ( new Consumer< BodyFixture > ()
				{
					@Override
					public void accept (BodyFixture bodyFixture)
					{
						bodyFixture.setDensity ( 512341 );
					}
				} );
				//box.setColor( Color.random() );
				box.setColor ( Color.random () );
				world.add ( box );
			}
		}
		if( Keyboard.isKeyDown( Keyboard.Key.Space ) )
		{
			box.getBody().applyForce( new Vector2( 0, -50 ) );
		}

		if( Keyboard.isKeyDown( Keyboard.Key.D ) )
		{
			box.getBody().applyForce( new Vector2( 21512331, 123414241 ) );
		}
		if( Keyboard.isKeyDown( Keyboard.Key.A ) )
		{
			box.getBody().applyForce( new Vector2( -21, 0 ) );
		}
		if( Mouse.isRightMouseDown () )
		{
			add( new Square( this.getMouseX (), this.getMouseY (), 50, 50 ) );
		}
	}
}
