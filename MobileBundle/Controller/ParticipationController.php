<?php

namespace MobileBundle\Controller;

use EvenementBundle\Entity\Participation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

use EvenementBundle\Entity\Evenement;


class ParticipationController extends Controller
{



    public function findallAction()
    {
        $events = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Participation')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }

/*
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
*/
    public function participerAction($idevenement,$iduser)
    {
        $em = $this->getDoctrine()->getManager();
        $p = new Participation();



        $em1 = $this->getDoctrine()->getManager();
        $user=$em1->getRepository('AppBundle:User')->findOneBy(array("id"=>$iduser));
        $p->setIdUser($user);

        $em1 = $this->getDoctrine()->getManager();
        $evenement=$em1->getRepository('EvenementBundle:Evenement')->findOneBy(array("id"=>$idevenement));
        $evenement->setNbrParticipant($evenement->getNbrParticipant()+1);
        $p->setIdEvenement($evenement);

        $em->persist($p);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($p);
        return new JsonResponse($formatted);
    }


    public function abondonnerAction($idevenement,$iduser)
    {

        $em = $this->getDoctrine()->getManager();
        $participation=$em->getRepository("EvenementBundle:Participation")->findOneBy(array("id_user"=>$iduser,"id_evenement"=>$idevenement));

        $em1 = $this->getDoctrine()->getManager();
        $evenement = $em1->getRepository('EvenementBundle:Evenement')->find($idevenement);
        $evenement->setNbrParticipant($evenement->getNbrParticipant()-1);
        

         $em2 = $this->getDoctrine()->getManager();
        $em2->remove($participation);
        $em2->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participation);
        return new JsonResponse($formatted);

    }

}
