package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;
	
	public ImageServiceImpl(RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }
	
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
    	try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }

    }
}