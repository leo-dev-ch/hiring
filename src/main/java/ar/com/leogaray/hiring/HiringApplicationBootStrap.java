package ar.com.leogaray.hiring;

import ar.com.leogaray.hiring.model.RolEntity;
import ar.com.leogaray.hiring.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HiringApplicationBootStrap implements CommandLineRunner {
    private final RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        rolRepository.save(new RolEntity(1, "ADMIN"));
        rolRepository.save(new RolEntity(2, "USER"));

    }
}
