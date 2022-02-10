package doryanbessiere.sapphireengine.fr.engine.scene.scenes;

import doryanbessiere.sapphireengine.fr.engine.camera.Camera;
import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;
import doryanbessiere.sapphireengine.fr.engine.game.Game;
import doryanbessiere.sapphireengine.fr.engine.scene.Scene;
import doryanbessiere.sapphireengine.fr.engine.shader.Shader;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {

    public LevelEditorScene(Game game) {
        super(game,"LevelEditor");
    }

    private Shader shader;

    @Override
    public void initialize() {
        this.camera = new Camera(new Vector2f());
        shader = new Shader(this.game, "assets/shaders/default.glsl");
        shader.compile();
        vaoID = glGenVertexArrays();

        glBindVertexArray(vaoID);
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();
        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int positionsSize = 3;
        int colorSize = 4;
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
        glEnableVertexAttribArray(1);
    }

    private float[] vertexArray = {
            100.5f, 0.5f, 0.0f,       1.0f, 0.0f, 0.0f, 1.0f,
            0.5f,  100.5f, 0.0f,       0.0f, 1.0f, 0.0f, 1.0f,
            100.5f, 100.5f, 0.0f ,      1.0f, 0.0f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f,
    };

    private int[] elementArray = {
        2, 1, 0,
        0, 1, 3
    };

    private int vaoID, vboID, eboID;

    @Override
    public void update(float dt) {
        camera.getPosition().x -= dt * 2f;
        getLogger().debug("update -> "+dt);
    }

    @Override
    public void render() {
        shader.use();
        shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        shader.uploadMat4f("uView", camera.getViewMatrix());
        // Bind the VAO that we're using
        glBindVertexArray(vaoID);

        // Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
        // Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        shader.detach();
    }
}
