<?php

namespace MobileBundle\Controller;

use EvenementBundle\Entity\Evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class EvenementController extends Controller
{



    public function findallAction()
    {
        $events = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }


    public function findbyidAction($id)
    {
        $event = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }

    public function findbynomAction($nom)
    {
        $event = $this->getDoctrine()->getManager()->getRepository('EvenementBundle:Evenement')->findBy(array("nom"=>$nom));
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }

    public function newAction($idmembre,$nom,$lieu,$description,$nbrmaxparticipant,$image)
    { $em = $this->getDoctrine()->getManager();
        $event = new Evenement();




      //  $user=$this->getUser();
        $em1 = $this->getDoctrine()->getManager();
        $user=$em1->getRepository('AppBundle:User')->findOneBy(array("id"=>$idmembre));

        $event->setNom($nom);
        $event->setLieu($lieu);

        //$event->setDate(new \DateTime($date));
        $event->setDescription($description);
        $event->setNbrParticipant(0);
        $event->setNbrMaxParticipant($nbrmaxparticipant);
        $event->setIdMembre($user);
        $event->setCreateur($user->getUsername());
        $event->setImage($image);

        $em->persist($event);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }
}
