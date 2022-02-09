package doryanbessiere.sapphireengine.fr.engine.scene.scenes;

import doryanbessiere.sapphireengine.fr.engine.exceptions.WindowCannotBeCreatedException;
import doryanbessiere.sapphireengine.fr.engine.game.Game;
import doryanbessiere.sapphireengine.fr.engine.scene.Scene;
import doryanbessiere.sapphireengine.fr.engine.shader.Shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {

    public LevelEditorScene(Game game) {
        super(game,"LevelEditor");
    }

    private Shader shader;

    @Override
    public void initialize() {
        shader = new Shader(this.game, "assets/shaders/default.glsl");
        shader.compile();
    }

    private float[] vertexArray = {
        0.5f, -0.5f, 0.0f,       1.0f, 0.0f, 0.0f, 1.0f,
        -0.5f,  0.5f, 0.0f,       0.0f, 1.0f, 0.0f, 1.0f,
        0.5f,  0.5f, 0.0f ,      1.0f, 0.0f, 1.0f, 1.0f,
        -0.5f, -0.5f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f,
    };

    private int[] elementArray = {
        2, 1, 0,
        0, 1, 3
    };

    private int vaoID, vboID, eboID;

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
        glUseProgram(shader.getShaderProgramID());
        shader.use();
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

        glUseProgram(0);
        shader.detach();
    }
}
