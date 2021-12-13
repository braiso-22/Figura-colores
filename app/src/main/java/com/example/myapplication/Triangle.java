package com.example.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class Triangle extends Shape {
    private FloatBuffer mFVertexBuffer, mFColorBuffer;
    private ByteBuffer mIndexBuffer;
    private FloatBuffer colors;

    public Triangle() {
        float[] mColorArray = {
                /*1f, 0f, 0f, 1f, //rojo
                1f, 0f, 1f, 1f, //rosa
                1f, 1f, 0f, 1f, //amarillo
                0f, 1f, 0f, 1f, //verde
                0f, 0f, 1f, 1f, //azul
                0f, 1f, 1f, 1f //celeste*/

                (float) Math.random()+0.3f, (float) Math.random(), (float) Math.random(), 1f,
                (float) Math.random(), (float) Math.random()+0.3f, (float) Math.random(), 1f,
                (float) Math.random(), (float) Math.random(), (float) Math.random()+0.3f, 1f,
                (float) Math.random()+0.3f, (float) Math.random(), (float) Math.random()+0.3f, 1f,
                (float) Math.random(), (float) Math.random()+0.3f, (float) Math.random()+0.3f, 1f
        };
        float vertices[] = {
                0f, 1.1f, 0f,
                -1f, 0f, 0f,
                -0.6f, -1.7f, 0f,
                0.6f, -1.7f, 0f,
                1f, 0f, 0f
        };
        mFVertexBuffer = buildFloatBuffer(vertices);
        mFColorBuffer = buildFloatBuffer(mColorArray);
        /* No necesitamos indices (solo si usamos drawElement*/
        byte indices[] = {0, 1, 2, 0, 2, 3, 0, 3, 4};
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);

    }

    public void draw(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mFColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 9, GL11.GL_UNSIGNED_BYTE, mIndexBuffer);
        //gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
    }

    private static FloatBuffer buildFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length *
                4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }
}
