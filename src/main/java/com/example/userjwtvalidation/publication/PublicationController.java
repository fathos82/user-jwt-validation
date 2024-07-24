package com.example.userjwtvalidation.publication;

import com.example.userjwtvalidation.publication.request.CreatePublicationRequest;
import com.example.userjwtvalidation.publication.request.EditPublicationRequest;
import com.example.userjwtvalidation.publication.response.EditedPublicationResponse;
import com.example.userjwtvalidation.publication.response.PublicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;
    @PostMapping("/create-publication")
    public ResponseEntity<PublicationResponse> createPublication(@RequestBody CreatePublicationRequest publicationRequest){
        Publication publication = publicationService.createPublication(publicationRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(publication.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping("/edit-publication")
    private ResponseEntity<EditedPublicationResponse> editPublication(@RequestBody  EditPublicationRequest editPublicationRequest){
        Publication publication = publicationService.updatePublication(editPublicationRequest);
        return ResponseEntity.ok(new EditedPublicationResponse(publication));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<PublicationResponse>> listPublicationByUserId(@PathVariable UUID userId){
        List<Publication> publicationList = publicationService.listPublicationByUserId(userId);
        List<PublicationResponse> publicationResponseList = publicationList.stream().map(PublicationResponse::new).toList();
        return ResponseEntity.ok(publicationResponseList);
    }

    @GetMapping("/explore")
    public ResponseEntity<Page<PublicationResponse>> explorePublications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Publication> publications = publicationService.findAll(pageable);
        return ResponseEntity.ok(publications.map(PublicationResponse::new));
    }

//    @DeleteMapping("admin/delete/{id}")
//    public ResponseEntity<PublicationResponse> deletePublication(@RequestBody DeletePublicationRequest messageRequest, @PathVariable String id){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Publication message = publicationService.createPublication(messageRequest);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(message.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).build();
//    }

//    @GetMapping("admin/list")
//    public ResponseEntity<List<AdminPublicationResponse>> listPublication(){
//        List<Publication> publicationList = publicationService.listAll();
//        return ResponseEntity.ok(publicationList.stream().map(AdminPublicationResponse::new).toList());
//    }


}
