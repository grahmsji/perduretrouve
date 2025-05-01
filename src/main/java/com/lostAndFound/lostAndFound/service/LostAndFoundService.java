package com.lostAndFound.lostAndFound.service;

import com.lostAndFound.lostAndFound.dto.lostfound.CreateLostFoundDto;
import com.lostAndFound.lostAndFound.dto.lostfound.LostFoundResponseDto;
import com.lostAndFound.lostAndFound.mapper.FoundMapper;
import com.lostAndFound.lostAndFound.mapper.LostFoundMapper;
import com.lostAndFound.lostAndFound.mapper.LostMapper;
import com.lostAndFound.lostAndFound.model.Found;
import com.lostAndFound.lostAndFound.model.Lost;
import com.lostAndFound.lostAndFound.model.LostFound;
import com.lostAndFound.lostAndFound.repository.FoundRepository;
import com.lostAndFound.lostAndFound.repository.LostFoundRepository;
import com.lostAndFound.lostAndFound.repository.LostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LostAndFoundService {

    private final LostRepository lostRepository;
    private final FoundRepository foundRepository;
    private final LostFoundRepository lostFoundRepository;
    private final LostFoundMapper lostFoundMapper;

    public List<LostFoundResponseDto> getAll() {
        return lostFoundRepository.findAll()
                .stream()
                .map(lostFoundMapper::toDto)
                .collect(Collectors.toList());
    }
    public LostFoundResponseDto createLostFound(String lostCode, String foundCode) {
        Lost lost = lostRepository.findByCode(lostCode)
                .orElseThrow(() -> new EntityNotFoundException("Lost item not found with code: " + lostCode));
        Found found = foundRepository.findByCode(foundCode)
                .orElseThrow(() -> new EntityNotFoundException("Found item not found with code: " + foundCode));

        // Créer une nouvelle entité LostFound
        LostFound lostFound = new LostFound();
        lostFound.setLost(lost);
        lostFound.setFound(found);
        lostFound.setStatus("Non traité");

        // Sauvegarder en base
        LostFound savedLostFound = lostFoundRepository.save(lostFound);

        // Retourner un DTO pour l'API
        return lostFoundMapper.toDto(savedLostFound);
    }
}
