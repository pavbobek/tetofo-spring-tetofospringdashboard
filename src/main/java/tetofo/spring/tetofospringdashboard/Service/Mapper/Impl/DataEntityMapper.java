package tetofo.spring.tetofospringdashboard.Service.Mapper.Impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Model.Entity.Enum.TagEntity;
import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;
import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IEntityMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

@Service
public class DataEntityMapper implements IEntityMapper<DataEntity, DataDTO> {

    @Override
    public DataDTO fromEntity(DataEntity r) throws MapperException {
        return r == null ? null : asDTO(r);
    }

    @Override
    public DataEntity toEntity(DataDTO s) throws MapperException {
        return s == null ? null : asEntity(s, null);
    }

    private static DataDTO asDTO(DataEntity r) throws MapperException {
        MapperException.requireNonNull(r, "DataEntity is null.");
        final DataDTO dataDTO = new DataDTO();
        dataDTO.setPayload(r.getPayload());
        dataDTO.setTags(asTagsDTO(r.getTags()));
        Set<DataDTO> members = new HashSet<>();
        if (r.getId() != null) {
            String idString = Long.toString(r.getId());
            final DataDTO recordDTO = new DataDTO();
            recordDTO.setTags(Collections.singleton(TagDTO.RECORD));
            recordDTO.setPayload(idString);
        }
        if (r.getMembers() != null && !r.getMembers().isEmpty()) {
            final Set<MapperException> mapperExceptions = new HashSet<>();
            members.addAll(
                r.getMembers()
                    .stream()
                    .map(item -> {
                        try {
                            return asDTO(item);
                        } catch (MapperException e) {
                            mapperExceptions.add(e);
                        }
                        return new DataDTO();
                    })
                    .collect(Collectors.toSet())
            );
            if (!mapperExceptions.isEmpty()) {
                throw new MapperException("Unable to map entity", mapperExceptions);
            }
        }
        if (!members.isEmpty()) {
            dataDTO.setMembers(members);
        }
        return dataDTO;
    }

    private static Set<TagDTO> asTagsDTO(Set<TagEntity> tagEntities) throws MapperException {
        MapperException.requireNonEmpty(tagEntities, "Invalid object, TagEntities is empty.");
        final Set<MapperException> mapperExceptions = new HashSet<>();
        final Set<TagDTO> tagDTOs = tagEntities
            .stream()
            .map(item -> {
                try {
                    return asTagDTO(item);
                } catch (MapperException e) {
                    mapperExceptions.add(e);
                }
                return TagDTO.DIRECTORY_PATH;
            })
            .collect(Collectors.toSet());
        if (!mapperExceptions.isEmpty()) {
            throw new MapperException("Unable to map tags", mapperExceptions);
        }
        return tagDTOs;
    }

    private static TagDTO asTagDTO(TagEntity tagEntity) throws MapperException {
        return switch (tagEntity) {
            case DIRECTORY_PATH -> TagDTO.DIRECTORY_PATH;
            case FILENAME -> TagDTO.FILENAME;
            case MESSAGE -> TagDTO.MESSAGE;
            case PERSISTENCE_FILE -> TagDTO.PERSISTENCE_FILE;
            case STRING -> TagDTO.STRING;
            default -> throw new MapperException(String.format("Unable to cast %s to TagDTO", tagEntity));
        };
    }
    
    private static DataEntity asEntity(DataDTO dataDTO, DataEntity parent) throws MapperException {
        MapperException.requireNonNull(dataDTO, "DataDTO is null.");
        final DataEntity dataEntity = new DataEntity();
        dataEntity.setPayload(dataDTO.getPayload());
        dataEntity.setParent(parent);
        dataEntity.setTags(asTagEntities(dataDTO.getTags()));
        dataEntity.setMembers(asMembers(dataDTO.getMembers(), dataEntity));
        dataEntity.setId(asId(dataDTO));
        return dataEntity;
    }

    private static Set<TagEntity> asTagEntities(Set<TagDTO> tagDTOs) throws MapperException {
        MapperException.requireNonEmpty(tagDTOs, "Invalid object, TagDTOS is empty.");
        final Set<MapperException> mapperExceptions = new HashSet<>();
        final Set<TagEntity> tagEntities = tagDTOs
            .stream()
            .map(item -> {
                try {
                    return asTagEntity(item);
                } catch(MapperException e) {
                    mapperExceptions.add(e);
                }
                return TagEntity.DIRECTORY_PATH;
            })
            .collect(Collectors.toSet());
        if (!mapperExceptions.isEmpty()) {
            throw new MapperException("Unable to map tags", mapperExceptions);
        }
        return tagEntities;
    }
    
    private static TagEntity asTagEntity(TagDTO tagDTO) throws MapperException {
        return switch (tagDTO) {
            case DIRECTORY_PATH -> TagEntity.DIRECTORY_PATH;
            case FILENAME -> TagEntity.FILENAME;
            case MESSAGE -> TagEntity.MESSAGE;
            case PERSISTENCE_FILE -> TagEntity.PERSISTENCE_FILE;
            case STRING -> TagEntity.STRING;
            default -> throw new MapperException(String.format("Unable to cast %s to TagEntity", tagDTO));
        };
    }

    private static Set<DataEntity> asMembers(Set<DataDTO> dataDTOs, DataEntity parent) throws MapperException {
        final Set<MapperException> mapperExceptions = new HashSet<>();
        final Set<DataEntity> dataEntities = new HashSet<>();
        dataDTOs.forEach(item -> {
            try {
                if (MapperException.requireNonNull(item.getTags(), "Invalid object, Tags is null.").contains(TagDTO.RECORD)) {
                    return;
                }
            } catch (MapperException e) {
                mapperExceptions.add(e);
            }
            try {
                dataEntities.add(asEntity(item, parent)); 
            } catch (MapperException e) {
                mapperExceptions.add(e);
            }
        });
        if (!mapperExceptions.isEmpty()) {
            throw new MapperException("Unable to map dataDTOs", mapperExceptions);
        }     
        return dataEntities.isEmpty() ? null : dataEntities;   
    }

    private static Long asId(DataDTO dataDTO) throws MapperException {
        if (dataDTO == null || dataDTO.getMembers() == null || dataDTO.getMembers().isEmpty()) {
            return null;
        }
        for (DataDTO memberDRO : dataDTO.getMembers()) {
            if (memberDRO.getTags() != null && memberDRO.getTags().contains(TagDTO.RECORD)) {
                try {
                    return memberDRO.getPayload() == null ? null : Long.valueOf(Long.parseLong(memberDRO.getPayload()));
                } catch (NumberFormatException e) {
                    throw new MapperException("Unable to convert %s to Long.".formatted(memberDRO.getPayload()), e);
                }
            }
        }
        return null;
    }

}