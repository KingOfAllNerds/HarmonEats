package com.techpointsos.harmoneats;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class arActivity extends AppCompatActivity {

    private StorageReference modelRef = FirebaseStorage.getInstance().getReference().child("Burger.glb");
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(arActivity.this, "Building Model", Toast.LENGTH_SHORT).show();
                try {

                    file = File.createTempFile("Burger", "glb");
                    modelRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            buildModel(file);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            AnchorNode anchorNode = new AnchorNode(hitResult.createAnchor());
            anchorNode.setRenderable(renderable);
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
// Maxscale must be greater than minscale
            node.getScaleController().setMaxScale(0.01f);
            node.getScaleController().setMinScale(0.005f);
            node.setLocalScale(new Vector3(.1f, .1f, .1f));
            node.getScaleController().setEnabled(true);
            node.getRotationController().setEnabled(true);
            node.setParent(anchorNode);
            node.setRenderable(renderable);
            arFragment.getArSceneView().getScene().addChild(anchorNode);
            node.select();
        });


    }



    private  ModelRenderable renderable;
    public void buildModel(File f){
        RenderableSource renderableSource = RenderableSource.builder().setSource(this, Uri.parse(f.getPath()) ,RenderableSource.SourceType.GLB)
                                                            .setRecenterMode(RenderableSource.RecenterMode.ROOT).build();
        ModelRenderable.builder().setSource(this, renderableSource)
                .setRegistryId(f.getPath())
                .build()
                .thenAccept(modelRenderable -> {
                    Toast.makeText(arActivity.this, "Model built", Toast.LENGTH_SHORT).show();
                    renderable = modelRenderable;
                });
    }
}